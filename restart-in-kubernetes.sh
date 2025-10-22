#!/bin/bash
# restart-in-kubernetes.sh
git add .
git commit -m "Auto commit before restarting tethys in Kubernetes"
mvn clean package -DskipTests
./build-docker.sh
kubectl delete pod -l app=tethys -n app
echo "Restarted tethys pods in Kubernetes."
echo "Use
kubectl get pods -n app
to check pod status."
echo "Use '
kubectl logs -f deployment/tethys -n app
' to view logs."