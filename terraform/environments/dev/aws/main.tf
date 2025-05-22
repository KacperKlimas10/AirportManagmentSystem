terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "5.84.0"
    }
  }

  # backend "s3" {
  #   bucket         = "s3-terraform-dev"
  #   key            = "path/to/state"
  #   region         = "eu-central-1"
  #   dynamodb_table = "example-table"
  #   encrypt = true
  # }
}

provider "aws" {
  region = "eu-central-1"
}

locals {
  tags = {
    Terraform   = "true"
    Environment = "dev"
  }
}

module "vpc" {
  source  = "terraform-aws-modules/vpc/aws"
  version = "5.18.1"

  name = var.project-vpc
  cidr = var.cidr

  azs             = var.availabity_zones
  private_subnets = var.private_subnets
  public_subnets  = var.public_subnets

  enable_nat_gateway          = true
  enable_vpn_gateway          = true
  default_security_group_name = ""

  tags = local.tags
}

module "security-group" {
  source  = "terraform-aws-modules/security-group/aws"
  version = "5.3.0"

  name   = var.security-group_name
  vpc_id = module.vpc.vpc_id

  ingress_with_cidr_blocks = [
    {
      from_port   = 8080
      to_port     = 8090
      protocol    = "tcp"
      description = "Allow web traffic"
      cidr_blocks = "0.0.0.0/0"
    },
    {
      from_port   = 22
      to_port     = 22
      protocol    = "tcp"
      description = "Allow SSH traffic"
      cidr_blocks = var.admin_ipv4
  }]

  egress_with_cidr_blocks = [
    {
      from_port   = 0
      to_port     = 65535
      protocol    = "-1"
      description = "Allow all traffic"
      cidr_blocks = "0.0.0.0/0"
  }]

  depends_on = [module.vpc]
  tags       = local.tags
}

module "eks" {
  source  = "terraform-aws-modules/eks/aws"
  version = "~> 20.31"

  cluster_name    = var.eks_name
  cluster_version = "1.32"

  cluster_endpoint_public_access = true

  enable_cluster_creator_admin_permissions = true

  cluster_compute_config = {
    enabled    = true
    node_pools = ["general-purpose"]
  }

  vpc_id     = module.vpc.vpc_id
  subnet_ids = [for subnet_id in module.vpc.private_subnets : subnet_id]

  depends_on = [module.vpc]
  tags       = local.tags
}

module "ec2_instance" {
  source  = "terraform-aws-modules/ec2-instance/aws"
  version = "5.7.1"

  count = 3

  name          = format("%s%s", var.ec2_name, tostring(count.index + 1))
  ami           = var.ec2_system_image
  instance_type = "t2.micro"

  key_name   = aws_key_pair.ec2-key.key_name
  monitoring = true

  vpc_security_group_ids      = [module.security-group.security_group_id]
  subnet_id                   = module.vpc.public_subnets[count.index]
  private_ip                  = cidrhost(module.vpc.public_subnets_cidr_blocks[count.index], count.index + 10)
  associate_public_ip_address = true

  depends_on = [module.vpc, aws_key_pair.ec2-key]
  tags       = local.tags
}

resource "aws_key_pair" "ec2-key" {
  key_name   = "ec2-key"
  public_key = var.ec2-rsa-pkey
}
