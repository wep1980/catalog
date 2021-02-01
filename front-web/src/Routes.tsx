// Classe responsavel pelas rotas da aplicação

import React from 'react';

/**
 * BrowserRouter -> encapsula a aplicação, regencia todas as rotas.
 * Switch -> decide qual rota sera renderizada.
 * Route -> Define a URL de cada rota na aplicação.
 */
import { BrowserRouter , Switch , Route} from 'react-router-dom'; 
import Navbar from './core/components/Navbar';

import Admin from './pages/Admin';
import Catalog from './pages/Catalog';
import ProductDetails from './pages/Catalog/components/ProductDetails';
import Home from './pages/Home';                                 
                                                                  


/**
 * Configurando o mecanismo de rotas.
 * exact -> Apenas quando for exatamante a "/" carrega a pagina Home
 * exact -> Apenas quando for exatamante a "/products" carrega a pagina products
 * <Navbar/> Trazendo o Navbar para todas as rotas
 * <Route path="/products/:productId" -> Na rota ja é passado o ID
 */
const Routes = () => (
   <BrowserRouter>
     <Navbar/>
      <Switch>

        <Route path="/" exact>
           <Home/>
        </Route>

        <Route path="/products" exact>
           <Catalog/>
        </Route>

        <Route path="/products/:productId"> 
           <ProductDetails/>
        </Route>

        <Route path="/admin">
           <Admin/>
        </Route>

      </Switch>
   </BrowserRouter>
);

export default Routes;