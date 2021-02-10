
import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { ReactComponent as ArrowIcon } from 'core/assets/images/arrow.svg'
// import { ReactComponent as ArrowIcon } from '../../../core/assets/images/arrow.svg' -> no arquivo tsconfig.json foi feita uma configuração "baseUrl": "./src",  para otimizar os ../../../ --- as pastas criadas dentro de src estao disponiveis no baseUrl
import ProductPrice from 'core/components/ProductPrice';
import { Product } from 'core/types/Product';
import { makeRequest } from 'core/utils/request';
import ProductDescriptionLoader from '../Loaders/ProductDescriptionLoader';
import ProductInfoLoader from '../Loaders/ProductInfoLoader';
import './styles.scss';

type ParamsType = {
    productId: string; // No back-end o id vem Long, mas na URL vem como string
}

const ProductDetails = () => {

    /**
     * Area do componente para chamada de APi , integração com back-end.
     * useParams<ParamsType> -> tipando o useParams
     * text-center -> classe bootstrap que centraliza a imagem
     * pr-5" -> padding para direita de 5
     */
    const { productId } = useParams<ParamsType>();

    /**
     * const que armazena o estado depois dos dados virem da api
     */
    const [product, setProduct] = useState<Product>();
    //console.log(product);


    /**
     * Por padrão o loading não aparece, entao o useState dele e falso. o Loading so aparece no inicio da requisição
     */
    const [isLoading, setIsLoading] = useState(false);

    console.log(isLoading);

    /**
     *  ` ` -> anotação de templeta string
     * makeRequest({ url : `/products/${productId}` }) -> Tras os dados da APi
     * Depois que a Api retorna(Promice) com sucesso then(RESPOSTA DO BACK-END) vai ser populado um estado interno do componente.
     * setProduct(response.data) -> o axios entrega o .data
     * { product?.price  &&  <ProductPrice price={product?.price}/>} -> se existir um price sera exibido o componente com o price
     */
    useEffect(() => {
        setIsLoading(true);
        //console.log(`componente de detalhes iniciado ${productId}`);
        makeRequest({ url: `/products/${productId}` })
            .then(response => setProduct(response.data))
            .finally(() => setIsLoading(false))
    }, [productId]);


    return (
        <div className="product-details-container">
            <div className="card-base border-radius-20 product-details">
                <Link to="/products" className="product-details-goback">
                    <ArrowIcon className="icon-goback" />
                    <h1 className="text-goback">voltar</h1>
                </Link>
                <div className="row">
                    <div className="col-6 pr-5">
                        {isLoading ? <ProductInfoLoader/> : (
                            <>
                                <div className="product-details-card text-center">
                                    <img src={product?.imgUrl} alt={product?.name} className="product-details-image" />
                                </div>
                                <h1 className="product-datails-name">
                                    {product?.name}
                                </h1>
                                {product?.price && <ProductPrice price={product?.price} />}
                            </>
                        )}
                    </div>
                    <div className="col-6 product-details-card">
                        {isLoading ? <ProductDescriptionLoader/> : (
                            <>
                                <h1 className="product-description-title">
                                    Descrição do produto
                                </h1>
                                <p className="product-description-text">
                                    {product?.description}
                                </p>
                            </>
                        )}

                    </div>
                </div>
            </div>

        </div>
    );
};

export default ProductDetails;