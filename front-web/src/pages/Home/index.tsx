import React from 'react';
import './styles.scss';
import { ReactComponent as  MainImage } from '../../core/assets/images/main-image.svg'; // importe da imagem main(Principal) -- ReactComponent as  MainImage renomeando o nome do component
import ButtonIcon from '../../core/components/ButtonIcon';
import { Link } from 'react-router-dom';


/**
 * Como essa ArrowFunction e simples e vai retornar apenas um HTML.
 * row -> linha do Bootstrap.
 * O BOOTASTRAP POR PADRÃO TRABALHA COM 12 COLUNAS, SE E COLOCADO COL-6 ESTAMOS TRABALHANDO COM METADO DO ESPEÇO
 * col-6" -> 6 colunas, classe Bootstrap.
 * ButtonIcon text="" -> passando o texto do butão dinamicamente
 */
const Home = () => (
   <div className="home-container">

       <div className="row home-content">

         <div className="col-6">
            <h1 className="text-title">
                Conheça o melhor <br/> catálogo de produtos
            </h1>

            <p className="text-subtitle">
                Ajudaremos você a encontrar os melhores <br/> produtos disponíveis no mercado.
            </p>
            <Link to="/catalog">
               <ButtonIcon text="inicie agora a sua busca"/>
            </Link>    
         </div> 

         <div className="col-6">
            <MainImage className="main-image"/>
         </div>

       </div>

   </div>
);


export default Home;