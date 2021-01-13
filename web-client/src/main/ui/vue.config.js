module.exports = {
    devServer: {
      proxy: {
        '^/vehiclehire': {
          target: 'http://localhost:8080',
          changeOrigin: true
        },
      }
    }
  }