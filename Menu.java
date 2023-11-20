import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Menu {
    public static void main(String args[]) {

        new Menu().rodaMenu(); 
    }

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

    private static void insertBanco(String usuario) {

        Connection conn = null;

        String sql = "INSERT INTO usuario (apelido) VALUES(?)";

        try {
            String url = "jdbc:sqlite:Mensagens.db";
            conn = DriverManager.getConnection(url);

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usuario);
            pstmt.executeUpdate();

            System.out.println("Usuário inserido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
             try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
    }

    public static void consultaMensagensPorApelido(String apelido) {
        Connection conn = null;
       
        try {
            String url = "jdbc:sqlite:Mensagens.db";
            conn = DriverManager.getConnection(url);

            String sql = "SELECT idRemetente, idDestinatario, texto FROM mensagem INNER JOIN usuario ON idDestinatario = usuario.rowid WHERE usuario.apelido = '"
                    + apelido + "'";
           
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String texto = rs.getString("texto");
                System.out.println(texto);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void insertmensagem(String remetente, String destinatario, String mensagem) {
        String sql = "INSERT INTO mensagem VALUES ((SELECT rowId from usuario WHERE apelido = ?), (SELECT rowId from usuario WHERE apelido = ?), ?);";

        Connection conn = null; // sempre usar para conectar no banco

        try {
            String url = "jdbc:sqlite:Mensagens.db"; // sempre utilizar para conectar o banco
            conn = DriverManager.getConnection(url); // sempre utilizado para conectar o banco
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, remetente);
            pstmt.setString(2, destinatario);
            pstmt.setString(3, mensagem);
            pstmt.executeUpdate();
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
