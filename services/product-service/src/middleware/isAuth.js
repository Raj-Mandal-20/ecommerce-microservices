const jwt = require('jsonwebtoken');
const AppError = require('../../shared/utils/AppError');
require('dotenv').config();
module.exports = (req, res, next) => {
    const authHeader = req.get('Authorization');
    if (!authHeader) {
        return next(new AppError('Not Authorized', 401));
    }

    const parts = authHeader.split(' ');
    if (parts.length !== 2 || parts[0] !== 'Bearer') {
        return next(new AppError('Invalid Authorization header format', 401));
    }

    const token = parts[1];

    let decodedToken;
    try {
        decodedToken = jwt.verify(token, process.env.SECRET_KEY);
    } catch (err) {
        return next(new AppError('Token Expired or Invalid', 401));
    }

    req.userId = decodedToken.userId;
    next();
}