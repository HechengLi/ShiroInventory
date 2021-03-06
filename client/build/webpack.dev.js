const webpack = require('webpack')
const path = require('path')
const merge = require('webpack-merge')
const common = require('./webpack.common.js')

module.exports = merge(common, {
  mode: 'development',
  output: {
    path: path.resolve(__dirname, '../dist'),
    filename: 'js/[name].[hash].js',
    publicPath: '/'
  },
  devtool: 'inline-source-map',
  devServer: {
    hot: true,
    historyApiFallback: true,
    host: '0.0.0.0',
    port: 8081,
    open: true,
    public: 'localhost:8081',
    proxy: {
      '/': {
        target: 'http://localhost:3000',
        bypass: function(req, res, proxyOptions) {
          if (req.headers.accept.indexOf('html') !== -1) {
            return '/index.html';
          }
        }
      }
    }
  },
  module: {
    rules: [
      {
        test: /\.s[ac]ss$/i,
        use: [
          // Creates `style` nodes from JS strings
          'style-loader',
          // Translates CSS into CommonJS
          'css-loader',
          // Compiles Sass to CSS
          'sass-loader',
        ]
      }
    ]
  },
  plugins: [
    new webpack.HotModuleReplacementPlugin()
  ]
})
