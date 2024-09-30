sudo docker build -t sde-frontend-app .
# sudo docker run -d --name frontend-service --network bridge -p 5173:5173 sde-frontend-app  
docker run -p 80:80 sde-frontend-app
