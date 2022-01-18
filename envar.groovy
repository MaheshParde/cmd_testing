Job_Name='test-auto'

version = "`cat VERSION`"

deploy = "["sit", "qa"]"

beGitUrl='git@10.212.0.139:ecgc-dev7/testing-auto.git'
beBranch='master'


beimage='docker-registry.cdacmumbai.in:443/erp-accounts-accounts-be.jar'

// feimage='docker-registry.cdacmumbai.in:443/erp-accounts-accounts-fe.jar:$BUILD_NUMBER '


uatbeimage='docker-registry.ecgc.in:443/erp-accounts-accounts-be.jar'
// uatfeimage='docker-registry.ecgc.in:443/erp-accounts-accounts-fe.jar'

 fromemail='ecgc-dev7@cdac.in'
 toemails='ecgc-dev7@cdac.in'
//qaemails='ecgc-dev7@cdac.in'
//toemails='ecgcdevops@cdac.in, shwetapal@cdac.in, vrushalic@cdac.in,aakashn@cdac.in,amiteshb@cdac.in,mohasin@cdac.in'
 replyto='ecgc-dev7@cdac.in'

