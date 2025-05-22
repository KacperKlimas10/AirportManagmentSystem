variable "admin_ipv4" {
  description = "The IP address to allow SSH access"
  type        = string
}

variable "project-vpc" {
  description = "The name of the VPC"
  type        = string
  default     = "lotnisko_project_vpc"
}

variable "cidr" {
  description = "The CIDR block for the VPC"
  type        = string
  default     = "10.0.0.0/16"
}

variable "private_subnets" {
  description = "The CIDR block for the private subnets"
  type        = list(string)
  default     = ["10.0.1.0/24", "10.0.2.0/24", "10.0.3.0/24"]
}

variable "public_subnets" {
  description = "The CIDR block for the public subnets"
  type        = list(string)
  default     = ["10.0.101.0/24", "10.0.102.0/24", "10.0.103.0/24"]
}

variable "availabity_zones" {
  description = "The availabity zones for the VPC"
  type        = list(string)
  default     = ["eu-central-1a", "eu-central-1b", "eu-central-1c"]
}

variable "eks_name" {
  description = "The name of the EKS cluster"
  type        = string
  default     = "lotnisko_eks_cluster"
}

variable "security-group_name" {
  description = "The name of the security group"
  type        = string
  default     = "lotnisko_security_group"
}

variable "ec2_name" {
  description = "The name of the EC2 instance"
  type        = string
  default     = "lotnisko_ec2_instance"
}

variable "ec2_system_image" {
  description = "The system image for the EC2 instance"
  type        = string
}

variable "ec2-rsa-pkey" {
  description = "The RSA private key for the EC2 instance"
  type        = string
}
