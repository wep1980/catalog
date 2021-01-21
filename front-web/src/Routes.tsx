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
import Home from './pages/Home';                                 
                                                                  


/**
 * Configurando o mecanismo de rotas.
 * exact -> Apenas quando for exatamante a "/" carrega a pagina Home
 * <Navbar/> Trazendo o Navbar para todas as rotas
 */
const Routes = () => (
   <BrowserRouter>
     <Navbar/>
      <Switch>

        <Route path="/" exact>
           <Home/>
        </Route>

        <Route path="/catalog">
           <Catalog/>
        </Route>

        <Route path="/admin">
           <Admin/>
        </Route>

      </Switch>
   </BrowserRouter>
);

export default Routes;