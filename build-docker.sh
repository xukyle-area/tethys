#!/bin/bash
# build-docker.sh

set -e

echo "🚀 Building Tethys Docker image..."

# 确保项目已构建
echo "📦 Building Maven project..."
mvn clean package -DskipTests

# 检查必要文件是否存在
if [ ! -f "target/tethys-1.0-SNAPSHOT.jar" ]; then
    echo "❌ Main JAR file not found!"
    exit 1
fi

echo "✅ All required files found"

# 构建Docker镜像
echo "🐳 Building Docker image..."
docker build -t tethys:latest .

echo "📋 Image built successfully!"
echo "📦 Image details:"
docker images | grep tethys

echo "🎉 Build completed!"