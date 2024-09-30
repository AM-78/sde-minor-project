# Step 1: Use an official Node.js image to build the application
FROM node:18-alpine AS builder

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the package.json and package-lock.json (if available)
COPY package*.json ./

# Step 4: Install dependencies
RUN npm install

# Step 5: Copy the rest of the project files to the container
COPY . .

# Step 6: Build the Vite project for production
RUN npm run build

# Step 7: Use a minimal Nginx image to serve the built project
FROM nginx:alpine

# Step 8: Copy the built files from the builder stage to Nginx's default directory
COPY --from=builder /app/dist /usr/share/nginx/html

# Step 9: Expose the port that Nginx will run on
EXPOSE 80

# Step 10: Start Nginx
CMD ["nginx", "-g", "daemon off;"]
