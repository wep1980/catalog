import React from 'react';
import './style.scss';
import { Link , NavLink } from 'react-router-dom'; // Importe do link que substitui a tag <a>



/**
 * <nav> -> tag do html 5 que adiciona uma semantica maior, comportamento semelhante e de uma div.
 * row -> class bootstrap, ja adiciona um diplay-flex
 * col-2 -> O elemento vai ocupar 2 colunas - class bootstrap
 * offset-2 -> adicionando um recua a esquerda de 2 colunas - class bootstrap.
 *  <Link to="/"> -> substitue a tag <a> e o to="" substitue o href=""
 * <Link to="/"> Rota para a pagina raiz do projeto.
 * NavLink to="/" activeClassName="active" -> Permite ativar no menu em qual rota(tela) eu estou(Home,Catalogo ou Admin).
 * <NavLink to="/" activeClassName="active" exact> -> No primeiro Navlink onde contem a rota "/" vai ser selecionado quando for exatamente so o caminho de "/"
 * 
 */
const Navbar = () => (
    <nav className="row bg-primary main-nav">

        <div className="col-2">
            <Link to="/" className="nav-logo-text">
                <h4>Catalog</h4>
            </Link>
        </div>

        <div className="col-6 offset-2">
            <ul className="main-menu">
                <li>
                    <NavLink to="/" activeClassName="active" exact>
                        HOME
                    </NavLink>
                </li>
                <li>
                    <NavLink to="/catalog" activeClassName="active">
                        CATÁLOGO
                    </NavLink>
                </li>
                <li>
                    <NavLink to="/admin" activeClassName="active">
                        ADMIN
                    </NavLink>
                </li>
            </ul>
        </div>

    </nav>
);

export default Navbar;