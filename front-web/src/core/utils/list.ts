/**
 *  Função JavaScript que gera uma lista de itens dinamicamente. Pode ser usando por toda aplicação, por isso esta dentro da pasta core/utils
 * .keys() pega todas as chaves geradas pela instancia do Array(amount)
 * Array.from() -> Gerando um array a partir das keys() do (Array(amount)
 * @param amount 
 */
export const generateList = (amount : number) => {
     return Array.from(Array(amount).keys());
}