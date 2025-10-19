const User = require('../models/User');
const AppError = require('../../shared/utils/AppError');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');



exports.signup = async (req, res, next) => {
    try {
        const { firstName, lastName, email, password } = req.body;
        const avatarPath = req.file ? req.file.path : '';
        const hashedPassword = await bcrypt.hash(password, 12);
        const newUser = new User({
            firstName,
            lastName,
            email,
            password: hashedPassword,
            avatar: avatarPath
        });

        const { password: _, ...userData } = newUser.toObject();

        await newUser.save();
        res.status(201).json({ message: "User registered successfully", user: userData });

    }
    catch (error) {
        next(error);
    }

}

exports.signin = async (req, res, next) => {
    try {
        const { email, password } = req.body;
        const isVaildUser = await User.findOne({
            email: email
        });
        if (!isVaildUser) {
            return next(new AppError('User Not Found!', 404));
        }
        const isMatch = await bcrypt.compare(password, isVaildUser.password);
        if (!isMatch) {
            return next(new AppError('Invalid Password!', 401));
        }

        const token = jwt.sign({
            userId: isVaildUser.id,
            email: isVaildUser.email
        }, process.env.SECRET_KEY, {
            expiresIn: '30d'
        });

        res.status(200).json({
            token: token,
            user: {
                userId: isVaildUser.id,
                email: isVaildUser.email,
                avatar: isVaildUser.avatar
            }
        });

    }
    catch (error) {
        next(error);
    }
}