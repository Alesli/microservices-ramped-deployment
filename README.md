# Kubernetes Helm

- [What to do](#what-to-do)
- [Sub-task 1: Helm charts](#sub-task-1--helm-chart-default-variables)
- [Sub-task 2: Helm chart helpers](#sub-task-2--helm-chart-helpers)

## What to do
In this module you will learn how to attach persistent storages to your applications. Also, you will understand how helm charts work.

## Sub-task 1: Helm chart default variables
1. Install helm [Official download link](https://helm.sh).
2. Add helm chart to deploy your applications. Make replica-count and namespace a helm values.
3. Add helm values file to store default values for helm variables.
4. Run helm using `helm install` command to deploy applications with default helm variables. Make sure, your applications are up and running.
 - `helm install userapp ./user-helm-chart --namespace k8s-program --create-namespace --wait `
 - `helm install postapp ./post-helm-chart --namespace k8s-program --wait `
5. Run helm once again, but this time set namespace and replica-count for `helm intall` to non-default values.
 - `helm install postapp ./post-helm-chart --namespace k8s-program --create-namespace --wait `
 - `helm template ./post-helm-chart --namespace=k8s-program`
 - `helm template ./post-helm-chart -- set postApp.replicaCount=2`

## Sub-task 2: Helm chart helpers
1. Create helm `_helpers.tpl` file and define next labels there:
    - current date : use helm generator for it's value
    - version
2. Make config-map use values as labels from helm `_helpers.tpl` file.

### all necessary commands:

helm status postapp --namespace k8s-program  
helm list --all-namespaces (or) helm ls -A
helm uninstall postapp ./post-helm-chart --namespace k8s-program
helm uninstall userapp ./user-helm-chart --namespace k8s-program
helm delete {release-name}

helm install --debug --dry-run postapp ./post-helm-chart
helm install --debug --dry-run userapp ./user-helm-chart

helm template postapp ./post-helm-chart --namespace k8s-program
helm template userapp ./user-helm-chart --namespace k8s-program