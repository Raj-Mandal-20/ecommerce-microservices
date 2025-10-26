const AppError = require('../../shared/utils/AppError');
const Product = require('../models/Product');

exports.getallProducts = async (req, res, next) => {
    try {
        const products = await Product.find();
        res.status(200).json({
            products
        })

    }
    catch (error) {
        next(error);
    }
}

exports.getProduct = async (req, res, next) => {
    try {
        const productId = req.params.id;
        const product = await Product.findById(productId);

        if (!product) {
            return next(new AppError('Product Not Found!', 404));
        }
        res.status(200).json({
            product
        })


    }
    catch (error) {
        next(error);
    }
}

exports.addNewProduct = async (req, res, next) => {
    try {
        const { name, description, price, stock } = req.body;
        const newProduct = new Product({
            name,
            description,
            price,
            stock,
            createdBy: req.userId
        });

        await newProduct.save();
        res.status(201).json({
            newProduct
        })
    }
    catch (error) {
        next(error);
    }
}


exports.updateProduct = async (req, res, next) => {
    try {
        const productId = req.params.id;
        const product = await Product.findById(productId);
        if (!product) {
            return next(new AppError('Product Not Found!', 404));
        }

        const { name, description, price, stock } = req.body;

        product.name = name;
        product.description = description;
        product.price = price;
        product.stock = stock;

        await product.save();
        res.status(200).json({
            message: 'Product updated Successfully!'
        })

    }
    catch (error) {
        next(error);
    }
}

exports.deleteProduct = async (req, res, next) => {
    try {
        const productId = req.params.id;
        const product = await Product.findById(productId);
        if (!product) {
            return next(new AppError('Product Not Found!', 404));
        }

        await Product.deleteOne({ _id: productId });
        res.status(200).json({
            message: 'Product Deleted Successfully!'
        })
    }
    catch (error) {
        next(error);
    }
}
exports.getProductById = async (productId) => {
    const product = await Product.findById(productId);
    if (!product) throw new Error('Product not found');

    return {
        product_id: product._id.toString(),
        name: product.name,
        description: product.description,
        price: product.price,
        stock: product.stock
    };
};
