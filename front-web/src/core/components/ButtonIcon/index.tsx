import React from 'react';
import './styles.scss'
import { ReactComponent as  ArrowIcon } from 'core/assets/images/arrow.svg';


/**
 * Criando um tipo do typeScript.
 * O componente vai receber um text
 */
type Props = {
   text : string;
}


/**
 * Vai retorna somente um HTML então pode ser utilizado ().
 * btn btn-primary -> classe de botão Bootrstrap.
 * d-flex -> Classe Bootstrap display-flex, coloca um elemento do lado do outro.
 * ButtonIcon recebe uma variavel da Props do type text
 */
const ButtonIcon = ({ text } : Props) => (
   <div className="d-flex">
       <button className="btn btn-primary btn-icon">
          <h5>{ text }</h5>
       </button>
       <div className="btn-icon-content">
           <ArrowIcon/>
       </div>
   </div>
);



export default ButtonIcon;