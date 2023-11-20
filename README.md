# projeto-final_java-JDBC-Sqlite

**O código a seguir é um programa que possui um menu interativo com quatro opções**
- adicionar usuario 
- enviar mensagem 
- ver mensagens 
- e sair 

**a ideia do código é ser um programa de envio e recebimento de mensagens entre usuários cadastrados além disso conta com a opção de visualizar mensagens**

- menu interativo :

 public void  rodaMenu() {
       
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("1 - adicionar pessoa\n");
            System.out.print("2 - enviar mensagem\n");
            System.out.print("3 - ver mensagens\n");
            System.out.print("4 - Sair \n");
            System.out.print("Qual opcao voce deseja ?\n");

            int op = scanner.nextInt();

            switch (op) {
                case 1:
                    System.out.println("Digite o nome do usuário a ser inserido:");
                    scanner.nextLine();
                    String usuario = scanner.nextLine();
                    insertBanco(usuario);
                    rodaMenu();
                    break;
                case 2:
                    System.out.println("digite o remetente: ");
                    String remetente = scanner.nextLine();

                    scanner.nextLine();

                    System.out.println("digite o destinatario: ");
                    String destinatario = scanner.nextLine();

                    System.out.println("digite a mensagem: ");
                    String mensagem = scanner.nextLine();

                    insertmensagem(remetente, destinatario, mensagem);
                        rodaMenu();
                    break;
                case 3:
                    System.out.println("Digite o nome/apelido do usuario ");
                    scanner.nextLine();
                    String apelido = scanner.nextLine();
                    consultaMensagensPorApelido(apelido);
                    rodaMenu();
                    break;

                case 4: 
                    System.out.println("Saindo .  . .");
                    scanner.close(); 
                    break;
                
                default:
                    rodaMenu() ;
                    break;
            }
        }
    }


No seguinte trecho, foi criado um método para apresentar as opções ao usuário. 
Foi utilizado o Scanner para receber a entrada de dados do usuário. 
Além disso, é utilizado um switch-case para determinar o caminho a ser seguido pelo código. 
Após isso, são solicitadas as informações a serem enviadas ao banco de dados. 
Depois que os dados com as respostas chegam ao banco, o usuário é redirecionado ao menu, em vez de simplesmente fechar o programa.



**O código possui três principais métodos:**
 
- insertBanco 
(esse método é responsável por inserir novos usuarios ao banco de dados do mensagens.db)
- consultaMensagensPorApelido 
(esse método é responsável por consultar as mensagens recebidas pelos usuarios através de seus nomes/apelidos)
- insertmensagem
(esse método é responsável por inserir a mensagem desejada ao banco e consequentemente ao usuario)
- close 
(é responsável por fechar e encerrar o programa após o uso)



Em resumo, as demais opções utilizam a biblioteca **JDBC** para conexão com o banco de dados **SQLite**. 
Usam uma URL para a base de dados SQLite Mensagens.db 
Além disso, são utilizados os métodos try, catch, e finally para lidar com possíveis erros e exceções que possam ocorrer.


