import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { ProductsResponse } from 'core/types/Product';
import { makeRequest } from 'core/utils/request';
import ProductCard from './components/ProductCard';
import ProductCardLoader from './components/Loaders/ProductCardLoader';
import './styles.scss';




const Catalog = () => {

    /**
     * productResponse -> variavel que simboliza o valor do estado
     * setProductsResponse -> variavel que muda o valor do estado
     */
    const [productResponse , setProductsResponse] = useState<ProductsResponse>();

    /**
     * Por padrão o loading não aparece, entao o useState dele e falso. o Loading so aparece no inicio da requisição
     */
    const [isLoading , setIsLoading] = useState(false); 
    

    // Quando o componente iniciar buscar a lista de produtos
    // Quando a lista de produtos estiver disponivel, popular um estado no componente e listar os produtos dinâmicamente

    /**
     * fetch() -> função javaScript que faz requisições, forma mais simples.
     * LIMITAÇÕES do fetch = Muito verboso, não tem suporte nativo para ler o progresso de upload de arquivos, não tem suporte nativo para enviar query strings.
     * 
     * Função que faz requisições
     */
    useEffect(() => {

        /**
         * Objeto de configuração das query strings para requisição da paginação
         */
        const params = {
            page : 0,
            linesPerPage : 12
        }

        /**
         * Antes de a requisição ser feita, o loader precisa ser iniciado
         */

        /**
         * data -> Objeto criado pelo axios
         * TODA vez que feita uma iteração sobre um elemento utilizando map() e preciso passar uma chave key={}, chave unica que ajuda o react a diferenciar um elemnto de um outro na listagem
         *  setIsLoading(true); -> Antes de ser feita a requisição é necessario iniciar o loader, e depois q a requisição terminar, e necessario finalizar o loader
         */
        setIsLoading(true);
        makeRequest({url : '/products' , params})
        .then(response => setProductsResponse(response.data))
        .finally(() => {  
            // finalizar o loader
            setIsLoading(false);
        })
    }, []);

    /**
     * <ProductCardLoader/> -> Loader iniciado antes da requisição.
     * isLoading ? <ProductCardLoader/> : -> enquanto estiver carregando a pagina exibe os isLoading(); ....Quando nao estiver carregando exibe o bloco abaixo
     * productResponse?.content.map(product => (
                    <Link to={`/products/${product.id}`} key={product.id}>
                        <ProductCard product={product}/>
                    </Link>
     */
    return (
        <div className="catalog-container">
            <h1 className="catalog-title">
                Catálogo de produtos
            </h1>
            <div className="catalog-products">
              { isLoading ? <ProductCardLoader/> : (
                  productResponse?.content.map(product => (
                    <Link to={`/products/${product.id}`} key={product.id}>
                        <ProductCard product={product}/>
                    </Link>
                ))
              )}
            </div>
        </div>
     );
}


export default Catalog;