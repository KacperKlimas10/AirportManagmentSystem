output "vpc_id" {
  value = module.vpc.vpc_id
}

output "eks_cluster_id" {
  value = module.eks.cluster_id
}

output "public_subnet_ipv4_cidr_blocks" {
  value = module.vpc.public_subnets_cidr_blocks
}

output "private_subnet_ipv4_cidr_blocks" {
  value = module.vpc.private_subnets_cidr_blocks
}

