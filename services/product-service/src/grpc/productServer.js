const grpc = require('@grpc/grpc-js');
const protoLoader = require('@grpc/proto-loader');
const path = require('path');
const { fileURLToPath } = require('url');
const { getProductById } = require('../controllers/productController');


// Load proto file
const PROTO_PATH = path.resolve(__dirname, './../../proto/product.proto');
const packageDef = protoLoader.loadSync(PROTO_PATH, {
  keepCase: true,
  longs: String,
  enums: String,
  defaults: true,
  oneofs: true,
});
const productProto = grpc.loadPackageDefinition(packageDef).product;

// gRPC service implementation
async function GetProduct(call, callback) {
  try {
    const product = await getProductById(call.request.product_id);
    callback(null, product);
  } catch (err) {
    console.error('Error in GetProduct:', err);
    callback({
      code: grpc.status.NOT_FOUND,
      message: err.message
    });
  }
}


// Create and start gRPC server
module.exports = function startGrpcServer() {
  const server = new grpc.Server();
  server.addService(productProto.ProductService.service, { GetProduct });

  const PORT = '0.0.0.0:50051';
  server.bindAsync(PORT, grpc.ServerCredentials.createInsecure(), (err, port) => {
    if (err) {
      console.error('Failed to start gRPC server:', err);
      return;
    }
    console.log(`🚀 gRPC server running on port ${port}`);
    server.start();
  });
}
