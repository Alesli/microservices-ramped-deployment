# Kubernetes Helm, Ingress

## Task 1 - Helm
Understand how helm charts work.

### Sub-task 1: Helm chart default variables
1. Install helm [Official download link](https://helm.sh).
2. Add helm chart to deploy your applications. Make replica-count and namespace a helm values.
3. Add helm values file to store default values for helm variables.
4. Run helm using `helm install` command to deploy applications with default helm variables. Make sure, your applications are up and running.
 - `helm install userapp ./user-helm-chart `
 - `helm install postapp ./post-helm-chart `
5. Run helm once again, but this time set namespace and replica-count for `helm intall` to non-default values.
```
helm install postapp ./post-helm-chart --namespace k8s-program --create-namespace --wait
```
```
helm install userapp ./user-helm-chart --namespace k8s-program --wait
```
```
helm template ./post-helm-chart --namespace=k8s-program
```
```
helm template ./post-helm-chart -- set postApp.replicaCount=3
```

### Sub-task 2: Helm chart helpers
1. Create helm `_helpers.tpl` file and define next labels there:
    - current date : use helm generator for it's value
    - version
2. Make config-map use values as labels from helm `_helpers.tpl` file.

### All necessary commands:

```
helm status postapp --namespace k8s-program
```  
`helm list --all-namespaces` (or) `helm ls -A`
```
helm uninstall postapp ./post-helm-chart --namespace k8s-program
```
```
helm uninstall userapp ./user-helm-chart --namespace k8s-program
```
`helm delete {release-name}`

`helm install --debug --dry-run postapp ./post-helm-chart`

`helm install --debug --dry-run userapp ./user-helm-chart`

`helm template postapp ./post-helm-chart --namespace k8s-program`

`helm template userapp ./user-helm-chart --namespace k8s-program`

## Task 2 - Ingress
Install ingress controller and route traffic to the applications.

### Sub-task 1: Ingress
1. Install ingress controller using helm chart. 
```
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
```
```
helm repo update
```
```
helm install app-ingress ingress-nginx/ingress-nginx --namespace k8s-program
```
```
kubectl --namespace k8s-program get services -o wide -w app-ingress-ingress-nginx-controller
```
 - [Guide](https://kube-workshop.benco.io/08-helm-ingress/)
 - [Quick start](https://kubernetes.github.io/ingress-nginx/deploy/)
2. Change Services type to ClusterIP to restrict external access.
3. Create ingress resource and route your traffic using rules.
```
kubectl apply -f ./ingress/ingress.yaml --namespace k8s-program
```
4. Configure rewrite-target of path using annotations. 
Example routing: from `http://localhost/posts/api/v1/greeting` to `http://posts:8080/api/v1/greeting`. 
5. Delete all resources
```
helm delete app-ingress --namespace k8s-program
```
 - [Rewrite Target - Docs](https://kubernetes.github.io/ingress-nginx/examples/rewrite/#rewrite-target)
 - [Ingress docs ](https://kubernetes.io/docs/concepts/services-networking/ingress/)
 - [Ingress Flags ](https://kubernetes.io/docs/reference/generated/kubectl/kubectl-commands#-em-ingress-em-)
