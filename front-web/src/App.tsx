import React from 'react'; // TODO COMPONENTE REACT PRECISA TER ESSE IMPORT
import './core/assets/styles/custom.scss'; // importando css global da aplicação
import './app.scss'; // Importe do CSS da pagina
import Routes from './Routes'; // Importe das rotas


/**
 * <Routes/> -> Toda a plicação esta baseada em rotas
 */
const App = () => {

  return (
      <Routes/>
  );
  
}


export default App; // Exportando o componente App para que possa ser usado na aplicação