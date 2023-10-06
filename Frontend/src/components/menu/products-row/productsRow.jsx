import ProductDisplay from '../product-display/ProductDisplay';

const ProductsRow = ({products, isOrder}) => {
  return (
    <>
      { products.map((prod, i) =>
        <ProductDisplay prod={prod} isOrder={isOrder}/>
      )}
    </>
  )
  
}

export default ProductsRow;