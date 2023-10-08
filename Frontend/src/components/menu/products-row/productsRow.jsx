import ProductDisplay from '../product-display/ProductDisplay';

const ProductsRow = ({products, isOrder}) => {
  return (
    <div className='flex-h'>
      { products.map((prod, i) =>
        <ProductDisplay prod={prod} isOrder={isOrder}/>
      )}
    </div>
  );
}

export default ProductsRow;