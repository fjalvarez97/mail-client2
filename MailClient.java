/**
 * A class to model a simple email client. The client is run by a
 * particular user, and sends and retrieves mail via a particular server.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class MailClient
{
    // The server used for sending and receiving.
    private MailServer server;
    // The user running this client.
    private String user;
    // Number of sent messages.
    private int mensajesEnviados;
    //Number of read messages:
    private int mensajesRecibidos;
    //Saves the longest message;
    private MailItem mensajeMasLargo;
    /**
     * Create a mail client run by user and attached to the given server.
     */
    public MailClient(MailServer server, String user)
    {
        this.server = server;
        this.user = user;
        mensajesEnviados = 0;
        mensajesRecibidos= 0;
        mensajeMasLargo = new MailItem("","","","");
    }

    /**
     * Return the next mail item (if any) for this user.
     */
    public MailItem getNextMailItem()
    {
        mensajesRecibidos += 1;
        return server.getNextMailItem(user);
    }

    /**
     * Print the next mail item (if any) for this user to the text 
     * terminal.
     */
    public void printNextMailItem()
    {
        MailItem item = server.getNextMailItem(user);
        if(item == null) {
            System.out.println("No new mail.");
        }
        else {
            item.print(); 
            if (item.getMessage().length() > mensajeMasLargo.getMessage().length())
            {   //Saves the messages if its longer than the actually longest one.
                mensajeMasLargo = item;
                mensajesRecibidos += 1;
            }
        }
    }

    /**
     * Send the given message to the given recipient via
     * the attached mail server.
     * @param to The intended recipient.
     * @param message The text of the message to be sent.
     */
    public void sendMailItem(String to, String message, String subject)
    {
        MailItem item = new MailItem(user, to,subject, message);
        server.post(item);
        mensajesEnviados += 1;
    }
    
    public String numeroMensajes()
    {
        //Return number of sent/received messages and the longest message you received, and the email that sent it.
        String mensajeDevolver;
        if(mensajesRecibidos > 0 )
        {
           mensajeDevolver = "Recibidos =" + mensajesRecibidos + " " + "Enviados = " + mensajesEnviados + " El mensaje mas Largo tiene " +
           mensajeMasLargo.getMessage().length() + " Caracteres, enviado por la direccion de correo: " + mensajeMasLargo.getFrom()  ;
        }
        else
        {
           mensajeDevolver = "Recibidos =" + mensajesRecibidos + " " + "Enviados = " + mensajesEnviados;
        }
        return mensajeDevolver;
    }
}
