import React from 'react';
import './style.scss';



/**
 * <nav> -> tag do html 5 que adiciona uma semantica maior, comportamento semelhante e de uma div.
 * row -> class bootstrap, ja adiciona um diplay-flex
 * col-2 -> O elemento vai ocupar 2 colunas - class bootstrap
 * offset-2 -> adicionando um recua a esquerda de 2 colunas - class bootstrap
 */
const Navbar = () => (
    <nav className="row bg-primary main-nav">
        <div className="col-2">
            <a href="link" className="nav-logo-text">
                <h4>Catalog</h4>
            </a>
        </div>
        <div className="col-6 offset-2">
            <ul className="main-menu">
                <li>
                    <a href="link" className="active">
                        HOME
                    </a>
                </li>
                <li>
                    <a href="link">
                        CATÁLOGO
                    </a>
                </li>
                <li>
                    <a href="link">
                        ADMIN
                    </a>
                </li>
            </ul>
        </div>
    </nav>
);

export default Navbar;