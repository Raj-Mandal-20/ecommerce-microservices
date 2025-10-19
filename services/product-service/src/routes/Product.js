const express = require('express');
const router = express.Router();
const productController = require('../controllers/productController');
const isAuth = require('../middleware/isAuth');


router.get('/products', isAuth, productController.getallProducts);
router.get('/products/:id', isAuth, productController.getProduct);
router.post('/products',isAuth, productController.addNewProduct);
router.put('/products/:id',isAuth, productController.updateProduct);
router.delete('/products/:id',isAuth, productController.deleteProduct);


module.exports = router;