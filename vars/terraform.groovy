def call(Map config = [:]) {
    pipeline('terraform', config)
}