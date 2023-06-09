##  microservices-ramped-deployment

- [What to do](#what-to-do)
- [Sub-task 1: Secrets and config-maps](#sub-task-1--secrets-and-config-maps)
- [Sub-task 2: Liveness and Readiness probes](#sub-task-2--liveness-and-readiness-probes)
- [Sub-task 3: Deployment strategies](#sub-task-3--deployment-strategies)
- [Sub-task 4: Deployment history](#sub-task-4--deployment-history)

## What to do
Manage secrets and properties for your k8s objects and study deployment strategies.

## Sub-task 1: Secrets and config-maps
1. Add Secrets object to k8s manifest to store database username and password.
2. Add config maps to store environment variables for application deployments.
3. Add sql scripts to init databases (create tables) to config maps.
4. Change k8s Deployment and StatefulSet objects to load these secrets and config-maps.

## Sub-task 2: Liveness and Readiness probes
1. Add endpoints for health checks at applications.
2. Add startup, liveness and readiness probes for Deployment objects at k8s manifest.
3. Add startup, liveness and readiness probes for StatefulSet objects at k8s manifest.

## Sub-task 3: Deployment strategies
Add a field to one of services, and perform Rolling-update deployment.
1. To Post service add a new field `topic (:String)`. This is the topic for the post. Specify it when creating a new post and when updating existing post. 
This field also should be returned at the responses for POST, PUT and GET operations.
2. Build a new docker image of application with changes and push it to the Docker Hub (specify another version of container).
3. Add Rolling-update deployment strategy to deployments at manifest files and apply the  manifest, so the old versions of microservices are deployed and running.
4. Set app version of app containers to the new one and apply manifest one more time. Make sure that new changes are deployed.
- `kubectl annotate deployment post-service kubernetes.io/change-cause="Updating image version to 2.0.0" -n k8s-program`

## Sub-task 4: Deployment history
As you deployed a new version of the application, see the history of deployments. The task is to roll back to previous version of the deployment without changing your manifest files.
Put in comments the solution of this task.
***
- Check the revision history of the deployment, showing the different versions of the deployment and their revision numbers:
`kubectl rollout history deployment/post-service -n k8s-program`
- Roll back to the previous version:
`kubectl rollout undo deployment/post-service --to-revision=<revision-number> -n k8s-program`
- Monitor the rollout status to ensure that the rollback is completed successfully:
`kubectl rollout status deployment/post-service -n k8s-program`