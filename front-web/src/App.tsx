import React from 'react'; // TODO COMPONENTE REACT PRECISA TER ESSE IMPORT
import './core/assets/styles/custom.scss'; // importando css global da aplicação
import './app.scss'; // Importe do CSS da pagina
import Navbar from './core/components/Navbar';


const App = () => {

  return (
      <Navbar/>
  );
  
}


export default App; // Exportando o componente App para que possa ser usado na aplicação