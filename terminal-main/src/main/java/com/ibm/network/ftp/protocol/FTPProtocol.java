/************************************************************************
  This software is subject to the terms of the IBM alphaBeans with Source 
  License Agreement available at 
  www.software.ibm.com/developer/alphabeans/source/license.html.
  
  Copyright (c) 1999 IBM Corporation and others. All rights reserved. 
  
  You must accept the terms of that agreement to use this software.
*************************************************************************/
  
package com.ibm.network.ftp.protocol;
import com.ibm.network.ftp.FileInfo;
import com.ibm.network.ftp.event.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * This class provides FTP protocol services.
 * All the frequently used FTP commands is implemented by this class 
 * and provided in the form of APIs.
 * This FTP protocol can connect to a FTP server outside a firewall 
 * and automatically interact in passive mode.
 * It listens for <a href="com.ibm.network.ftp.event.CommandEvent.html"> CommandEvents </a> by implementing <a href="com.ibm.network.ftp.event.CommandListener.html"> CommandListener </a>.
 * The <a href="com.ibm.network.ftp.event.CommandEvent.html"> CommandEvent </a> carries the command name to be executed.
 * This class throws <a href="com.ibm.network.ftp.event.StatusEvent.html"> StatusEvent </a> which carries the current
 * status of FTP connection.
 * This class throws <a href="com.ibm.network.ftp.event.LocalFileListEvent.html"> LocalFileListEvent </a> and <a href="com.ibm.network.ftp.event.RemoteFileListEvent.html"> RemoteFileListEvent </a>
 * which carry the local and remote current directory and their
 * file listing.
 * The details of local and remote files are stored in a vector of
 * <a href="com.ibm.network.ftp.FileInfo.html"> FileInfo </a> objects. 
 * @author  Rajesh Singh
 * @version 1.03
 *
 * @see    com.ibm.network.ftp.event.CommandListener
 * @see     com.ibm.network.ftp.event.StatusEvent
 * @see     com.ibm.network.ftp.event.RemoteFileListEvent
 * @see     com.ibm.network.ftp.event.LocalFileListEvent
 * @see     com.ibm.network.ftp.FileInfo
 */
public class FTPProtocol implements CommandListener,Serializable{
	private static final long serialVersionUID = -2139104011320024339L;

	/**
     * The reference to local protocol.
     * This reference will be used to set the protocol type 
     * of File or Directory commands.
     */
	transient private Protocol lProtocol;

	/**
	 * The reference to remote protocol.
	 * This reference will be used to set the protocol type
	 * of File or Directory commands.
	 */
	transient private Protocol rProtocol;

	/**
	 * The reference to local protocol.
	 * This reference will be used to make direct method calls
	 * on the local protocol.
	 */
	transient private Local localProtocol;
	/**
	 * The reference to remote protocol.
	 * This reference will be used to make direct method calls
	 * on the remote protocol.
	 */
	transient private Remote remoteProtocol;
	/**
	 * The reference to command interpreter.
	 * The type of command in the command event is interpreted
	 * by the command Interpreter.
	 */
	transient private CommandInterpreter commandInterpreter;

    /**
     * This variable stores the name of the socksProxyHost.
     */ 
    private String socksProxyHost=new String(" ");
    
    /**
     * This variable stores the  socksProxyPort.
     */
    private String socksProxyPort=new String("1080");
    
    /**
     * Vector of StatusListener.
     * This variable is a java.util.Vector which stores the
     * list of all the registered status listeners.
     */ 
    transient private Vector<StatusListener> statusListener=new Vector<StatusListener>();
    
    /**
     * Vector of LocalFileListListener.
     * This variable is a java.util.Vector which stores the
     * list of all the registered localFileList listeners.
     */ 
    transient private Vector<LocalFileListListener> localFileListListener=new Vector<LocalFileListListener>();
    
    /**
     * Vector of RemoteFileListListener.
     * This variable is a java.util.Vector which stores the
     * list of all the registered remoteFileList listeners.
     */
    transient private Vector<RemoteFileListListener> remoteFileListListener=new Vector<RemoteFileListListener>();
    
    /**
     * This variable stores the present status of FTP connection.
     */ 
    private String status;
    /**
     * This variable stores the present data transfer mode.
     */ 
    private String type=new String(" ");
    
/**
 * Creates an object of FTPProtocol class.
 */
public FTPProtocol ( ) {
    
    //create the instance of remote
    rProtocol=new Remote(this);
    
	//create the instance of local
	lProtocol=new Local(this);

	//create the instance of remote protocol
/** BIPIN
	remoteProtocol=Remote.getRemoteProtocol(this);
*/
	localProtocol  = (Local)lProtocol;
	remoteProtocol = (Remote)rProtocol;
    
    //create the instance of command interpreter
    commandInterpreter= new CommandInterpreter(this);
}
/**
 * Aborts the last issued command.
 * This command will abort the previously issued command if it
 * is running. If the execution of previously issued command is complete
 * it will do nothing.<b> Not implemented in this version.</b>
 */
public void abort() {
    remoteProtocol.abort();
    return;
}
/**
 * Adds the specified LocalFileListListener to receive LocalFileListEvents
 * from this FTPProtocol object. 
 *
 * @param listener LocalFileListListener
 * @see     com.ibm.network.ftp.event.LocalFileListListener 
 */
public void addLocalFileListListener(LocalFileListListener listener ) {
    localFileListListener.addElement(listener);
    //as soon as the program is run it should set the local
    //fileList and directory
	//Local local=Local.getLocalProtocol(this);
	sendLocalList(localProtocol.getLocalFileList(),localProtocol.getCurrentDir());
    
    return;
}
/**
 * Adds the specified RemoteFileListListener to receive RemoteFileListEvents
 * from this FTPProtocol object.
 *
 * @param listener RemoteFileListListener
 * @see     com.ibm.network.ftp.event.RemoteFileListListener
 */
public void addRemoteFileListListener(RemoteFileListListener listener ) {
    remoteFileListListener.addElement(listener);
    return;
}
/**
 * Adds the specified StatusListener to receive StatusEvents 
 * from this FTPProtocol object.
 *
 * @param   listener StatusListener
 * @see     com.ibm.network.ftp.event.StatusListener
 */
public synchronized void addStatusListener (StatusListener listener ) {
    statusListener.addElement(listener);
    return;
}
/**
 * Changes directory whose pathname is specified by the first string parameter. 
 * The second boolean parameter specifies whether the change directory is
 * for local machine or remote FTP server. If second parameter is true then 
 * command is for remote machine else it is for local machine. This method throws
 * IllegalArgumentException if user attempts to change directory without specifing
 * the new directory name.
 * 
 * @param newName the new directory name.
 * @param remote this boolean argument specifies whether the change directory
 *               is for local machine or FTP server.
 * @exception IllegalArgumentException If user tries to change directory without specifying
 *            the new directory name.
 */
public void changeDir (java.lang.String newName,boolean remote ) throws IllegalArgumentException {
    if(newName!=null){
        if(remote){
            FtpDirectory ftpDirectory=new FtpDirectory(newName,this.rProtocol);
            ftpDirectory.changeDir();
            sendRemoteList(remoteProtocol.fileList(),remoteProtocol.getCurrentDir());
        }
        else{
            FtpDirectory ftpDirectory=new FtpDirectory(newName,this.lProtocol);
            ftpDirectory.changeDir();
			//Local local=Local.getLocalProtocol(this);
            sendLocalList(localProtocol.fileList(),localProtocol.getCurrentDir());
        }
    }
    else {
        this.sendMessage("Trying to change directory without specifying the name \n" );
        throw new IllegalArgumentException("Directory name not specified in change directory"); 
    }       
}
/**
 * Implements the commandPerformed method defined in CommandListener interface.
 * This method receives the CommandEvent from the CommandEvent sources and
 * passes it on to CommandInterpreter. 
 *
 * @param cevent CommandEvent
 * @exception IllegalArgumentException If user calls this method with CommandEvent argument as null.
 * @see     com.ibm.network.ftp.event.CommandListener
 *
 */
@Override
public void commandPerformed(CommandEvent cevent) throws IllegalArgumentException{
    if(cevent!=null){//check that the command that is 
        // coming is not null
        commandInterpreter.interpretCommand(cevent);
    }
    else{
        throw new IllegalArgumentException("Command Events is null in CommandPerformed"); 
    }   
    return;
}
/**
 * Configures socksProxyHost and socksProxyPort.
 * The socksProxyHost and socksProxyPort are set by this method.
 * The socks host will be used by the protocol for connecting to servers,
 * when the client machine is behind a firewall.
 *
 * @param hostName the socksProxyHost IP or domain name.
 * @param port the socksProxyPort.
 * @exception IllegalArgumentException If any of the arguments are null.
 */
public void configureSocks(String hostName,String port ) throws IllegalArgumentException{
    if(hostName!=null && port!=null){
        //call the protocols configure socks method
        //no need to check whether the strings are null
        remoteProtocol.configureSocks(hostName.trim(),port);
    }
    else{
        throw new IllegalArgumentException("Either hostname or port is null in configure socks");   
    }   
    return;
}
/**
 * Connects to the specified host.
 * Creates a FTP connection to the named host.
 * 
 * @param hostName the IP address of the FTP server.
 * @exception IllegalArgumentException If the host name specified is null.
 */
public void connect( String hostName) throws IllegalArgumentException {
    
    //check that the host name is not null and call the 
    // corresponding method of remote protocol.
    if(hostName!=null){
        remoteProtocol.connect(hostName);
    }
    else{
        throw new IllegalArgumentException("Hostname is null in connect"); 
    }   
    return;
}
/**
 * Deletes the  specified file.
 * This method deletes the file name as specified by the first
 * parameter. The second parameter decides whether the deletion
 * has to be on local machine or remote FTP server.
 * If second parameter is true then delete remote file else delete local file.
 *
 * @param fileName name of the file to be deleted.
 * @param remote this boolean argument specifies whether the file deletion
 *               is for local machine or FTP server.
 * @exception IllegalArgumentException If the file name specified is null.
 */
public void deleteFile (String fileName,boolean remote )throws IllegalArgumentException {
    if(fileName!=null){//check that the file name is not null
        
        if(remote){
            FtpFile ftpFile=new FtpFile(fileName,this.rProtocol);
            ftpFile.deleteFile();
            sendRemoteList(remoteProtocol.fileList(),remoteProtocol.getRemoteCurrentDir());
        }
        else{
            FtpFile ftpFile=new FtpFile(fileName,this.lProtocol);
            ftpFile.deleteFile();
			//Local local=Local.getLocalProtocol(this);
            sendLocalList(localProtocol.fileList(),localProtocol.getLocalCurrentDir());
        }
    }
    else{
        throw new IllegalArgumentException("Filename is null in delete File"); 
    }       
    return;
}
/**
 * Disconnect from the FTP server.
 * This method will close any active FTP connection. If there is
 * no active FTP connection then it will throw proper error message. Throws 
 * RemoteFileListEvent to refresh the view, to all of the
 * registered listeners.
 */
public void disconnect ( ) {
    remoteProtocol.disconnect();
    sendRemoteList(remoteProtocol.getRemoteFileList(),remoteProtocol.getRemoteCurrentDir());
    return;
}
/**
 * This method lists files in the current working directory.
 * The boolean value decides whether the listing is for local machine
 * or remote FTP server.
 * If the parameter is true then listing is for FTP server or else
 * the listing is for local machine.
 * This method will throw LocalFileListEvent or RemoteFileListEvent
 * depending on whether the listing is for local machine or remote FTP server.
 *
 * @param remote this boolean argument specifies whether the file listing
 *               is for local machine or FTP server.
 */
public void fileList( boolean remote) {
    if(remote){ //true means the list is for remote.
        rProtocol.fileList();
        sendRemoteList(remoteProtocol.getRemoteFileList(),remoteProtocol.getRemoteCurrentDir());
    }
	else{ //false implies that the list is for local.
        lProtocol.fileList();
		//Local local=Local.getLocalProtocol(this);
        sendLocalList(localProtocol.getLocalFileList(),localProtocol.getLocalCurrentDir());
    }       
    return;
}
/**
 * Gets the current working directory.
 * This method executes the proper commands to determine the 
 * current working directory.The "pwd" command is used for 
 * remote FTP server and System property "user.dir" is used for
 * local machine.
 * The boolean variable decides whether the command is for 
 * local or remote machine.
 * If argument is true then the command is for FTP server else
 * it is for local machine.
 *  
 * @param remote this boolean argument specifies whether this method 
 *               is executes for local machine or FTP server.
 * @return current working  directory on local machine or FTP server.
 */
public String getCurrentDir(boolean remote ) {
    if(remote){// command for the remote protocol
        return rProtocol.getCurrentDir();
    }
    else{  // command for the local protocol
        return lProtocol.getCurrentDir();
    }       
}
/**
 * Download the specified file from the FTP server.
 * This method will download the specified file from the FTP
 * server and store it on the local machine with the same file
 * name.
 *
 * @param fileName name of the file to be downloaded.
 * @exception IllegalArgumentException If the specified file name is null.
 */
public void getFile(String fileNameRemote, String fileNameLocal )throws IllegalArgumentException  {
	//if the file name is not null then only call the method
	// on protocol
	if(fileNameRemote!=null){
		remoteProtocol.getFile(fileNameRemote, fileNameLocal);
		//Local local=Local.getLocalProtocol(this);
		//if (fileNameLocal == null)	// downloaded in current directory, refresh
		//	sendLocalList(localProtocol.fileList(),localProtocol.getCurrentDir());
	}
	else{
		throw new IllegalArgumentException(" File Name is null in get file") ;
	}
	return;
}
/**
 * Returns the current local directory.
 * This method returns the String value of the current local
 * working directory.
 *
 * @return current local working directory.
 */
public String getLocalDir( ) {
	return lProtocol.getCurrentDir();
}
/**
 * Returns a Vector of FileInfo objects.
 * Each FileInfo element in the vector corresponds to a file in the local
 * working directory. FileInfo contains other information
 * like size,date and time for each file.
 *
 * @return vector of FileInfo objects representing the files in the current
 *              local working directory.
 * @see     com.ibm.network.ftp.FileInfo
 */
public java.util.Vector getLocalFileList( ) {
	//Local local=Local.getLocalProtocol(this);
	return localProtocol.getLocalFileList() ;
}
/**
 * Returns the FTP server working directory.
 * This method returns the String value of the current working directory
 * on the FTP server.
 *
 * @return current working directory on FTP server.
 */
public String getRemoteDir ( ) {
	return remoteProtocol.getRemoteCurrentDir();
}
/**
 * Returns a Vector of FileInfo objects.
 * Each FileInfo element in the vector corresponds to a file on the FTP server
 * working directory. FileInfo contains other information
 * like size,date and time for each file.
 *
 * @return vector of FileInfo objects representing the files in the current
 *              working directory of FTP server.
 * @see com.ibm.network.ftp.FileInfo
 */
public java.util.Vector getRemoteFileList ( ) {
	return remoteProtocol.getRemoteFileList();
}
/**
 * Returns socksProxyHost.
 * This method returns the String value of the socksProxyHost
 * used by the protocol to connect to FTP servers outside the Firewall.
 * If the user hasn't configured the socks properties ,
 * this method will return null.
 *
 * @return the socksProxyHost name if initialized or else returns null.
 */
public String getSocksProxyHost( ) {
    //String socksHost=System.getProperty("socksProxyHost");
    return socksProxyHost;
}
/**
 * Returns socksProxyPort.
 * This method returns the String value of the socksProxyPort
 * used by the protocol to connect to FTP servers outside the Firewall.
 * If the user hasn't configured the socks properties ,
 * this method will return null.
 *
 * @return the socksProxyPort if initialized or else returns null.
 */
public String getSocksProxyPort ( ) {
    //String socksPort=System.getProperty("socksProxyPort");
    return socksProxyPort;
}
/**
 * This method returns the latest status of connection to FTP server.
 * In most of the cases this method returns status of last
 * issued command.
 *
 * @return  the current FTP connection status.
 */
public String getStatus ( ) {
    return this.status;
    
}
/**
 * This method returns the data transfer type,ie., either "ASCII" or "BINARY".
 * By default,it returns "ASCII".
 *
 * @return  the data transfer type.
 */
 public String getType()
    {
        return this.type;
    }   
/**
 * This method lets the user login to the connected FTP server.
 * It takes the user name and password as the  parameters. 
 * User can either specify his login name and password
 * or connect as a anonymous user. In the later case he/she has to
 * specify his/her email id as the password.
 * 
 * @param userName user name of the user profile on the FTP server.
 * @param passwd password of user profile on the FTP server.
 */
public void login (java.lang.String userName,java.lang.String passwd ) throws IllegalArgumentException {
    //check that the parameters are not null and then call the
    //login method of remote protocol
    if(userName!=null && passwd!=null){
        remoteProtocol.login(userName,passwd);
        
    }
    else{
        throw new IllegalArgumentException("User name or passwd is null in login"); 
    }   
    return;         
}
/**
 * This method creates a directory in the current working directory.
 * The first String parameter to this method specifies the directory
 * name. The second boolean parameter decides whether the directory
 * has to be created on local machine or on the FTP server.
 * If second parameter is true then create directory on FTP server.
 * If second parameter is false then create directory on local machine.
 *
 * @param dirName name of the directory to be created.
 * @param remote this boolean argument specifies whether the create directory
 *               is for local machine or FTP server.
 * @exception IllegalArgumentException If the specified directory name is null.
 */
public void makeDir(java.lang.String dirName,boolean remote)throws IllegalArgumentException{
    if(dirName!=null){
        if(remote){//if the command is for remote
            FtpDirectory ftpDirectory=new FtpDirectory(dirName,this.rProtocol);
            ftpDirectory.makeDir();
            sendRemoteList(remoteProtocol.fileList(),remoteProtocol.getRemoteCurrentDir());
		}
        else{ //if the command is for local
            FtpDirectory ftpDirectory=new FtpDirectory(dirName,this.lProtocol);
            ftpDirectory.makeDir();
			//Local local=Local.getLocalProtocol(this);
            sendLocalList(localProtocol.fileList(),localProtocol.getLocalCurrentDir());
        }
    }   
    else{
        throw new IllegalArgumentException("Directory name not specified in make directory"); 
    }       
    return;
}
/**
 * This method lets the user to configure the FTP server in
 * passive mode. In this case the FTP server opens a passive
 * data connection( Server Socket). The FTP client opens a
 * active data connection. This method will have to be used for
 * severs located outside firewall since they can't open a connection 
 * back to the host.The protocol automatically takes care of 
 * changing to passive mode if active mode attempt fails.
 */
public void passiveServer ( ) {
    remoteProtocol.passive();
    return;
}
/**
 * Upload the specified file to the FTP server.
 * This method uploads the specified file to the FTP
 * server and store it on the server with the same file
 * name.The method uses "STOR" command which overwrites any files with
 * the same name as file to be uploaded.
 *
 * @param fileName name of the file to be uploaded on to the FTP server.
 * @exception IllegalArgumentException If the specified file name is null.
 */
public void putFile (String localFileName, String remoteFileName ) throws IllegalArgumentException{
    //check that the file name is not null
	if(localFileName!=null)
	{
		remoteProtocol.putFile(localFileName, remoteFileName);
		//if (remoteFileName == null)
		//	sendRemoteList(remoteProtocol.fileList(),remoteProtocol.getRemoteCurrentDir());
	}
	else{
		throw new IllegalArgumentException("No name specified in put file");
	}
	return;
}
/**
 * Remove the named directory.
 * This method deletes the named directory form the FTP server
 * or the local machine depending on the second boolean parameter.
 * If second parameter is true then delete directory on the FTP server.
 * If second parameter is false delete directory from the local machine.
 * For deleting files on local machine, the directory has to be empty
 * or else this command fails.
 *
 * @param dirName name of the directory to be deleted.
 * @param remote boolean argument specifies whether the delete directory
 *               is for local machine or FTP server.
 * @exception IllegalArgumentException If the specified directory name is null.
 */
public void removeDir(java.lang.String dirName,boolean remote )throws IllegalArgumentException{
	if(dirName!=null){
		if(remote){//if the command is for remote
			FtpDirectory ftpDirectory=new FtpDirectory(dirName,this.rProtocol);
			ftpDirectory.removeDir();
			sendRemoteList(remoteProtocol.fileList(),remoteProtocol.getRemoteCurrentDir());
		}
		else{ //if the command is for local
			FtpDirectory ftpDirectory=new FtpDirectory(dirName,this.lProtocol);
			ftpDirectory.removeDir();
			//Local local=Local.getLocalProtocol(this);
			sendLocalList(localProtocol.fileList(),localProtocol.getLocalCurrentDir());
		}
	}
	else{
		throw new IllegalArgumentException("Directory name is null in remove directory");
	}
	return;
}
/**
 * Removes the specified LocalFileListListener so that it no longer receives LocalFileListEvents from this protocol.
 * LocalFilelList events occur whenever a new local file listing has to
 * be notified to the registered listeners.
 *
 * @param listener LocalFileListListener to be removed from LocalFileListListener listeners list.
 * @see     com.ibm.network.ftp.event.LocalFileListListener
 */
public synchronized void removeLocalFileListListener(LocalFileListListener listener ) {
	localFileListListener.removeElement(listener);
	return;
}
/**
 * Removes the specified RemoteFileListListener so that it no longer receives RemoteFileListEvents from this protocol.
 * RemoteFilelList events occur whenever a new remote file listing has to
 * be notified to the registered listeners.
 *
 * @param listener RemoteFileListListener to be removed from RemoteFileListListener listeners list.
 * @see     com.ibm.network.ftp.event.RemoteFileListListener
 */
public synchronized void removeRemoteFileListListener(RemoteFileListListener listener ) {
    remoteFileListListener.removeElement(listener);
    return;
}
/**
 * Removes the specified StatusListener so that it no longer receives StatusEvents from this protocol.
 * StatusEvent occurs whenever a new status has to be notified to the registered listeners. 
 *
 * @param listener StatusListener to be removed from the StatusListener listeners list.
 * @see     com.ibm.network.ftp.event.StatusListener
 */
public synchronized void removeStatusListener( StatusListener listener) {
    statusListener.removeElement(listener);
    return;
}
/**
 * Renames the file specified by the first argument to the name given by second argument.
 * The first argument specifies the file that has to be renamed. The second argument
 * specifies the new name for the file.The file named by the first argument should exist
 * in the current working directory.
 * The third boolean parameter determines whether the rename has to be done on
 * local machine or on remote machine.
 * If third parameter is true then rename on FTP server else on local machine.
 *
 * @param oldName name of the file to be renamed.
 * @param newName new name of the file to be renamed.
 * @param remote this boolean argument specifies whether the rename file
 *               is for local machine or FTP server.
 * @exception IllegalArgumentException If any of the newName or oldName parameters are null.
 */
public void rename(String oldName,String newName,boolean remote )throws IllegalArgumentException {
    //check that the oldname and newname are not null
    if(oldName!=null && newName!=null){
        if(remote){
            FtpFile ftpFile=new FtpFile(oldName,this.rProtocol);
            ftpFile.renameFile(newName);
            sendRemoteList(remoteProtocol.fileList(),remoteProtocol.getRemoteCurrentDir());
        }//end of if
        else{
            FtpFile ftpFile=new FtpFile(oldName,this.lProtocol);
            ftpFile.renameFile(newName);
			//Local local=Local.getLocalProtocol(this);
            sendLocalList(localProtocol.fileList(),localProtocol.getLocalCurrentDir());
        }//end of else
    }//end of outer if  
    else{
        throw new IllegalArgumentException("Either oldname or new name is null in rename file") ;
    }   
    return;
}
/**
 * Sends LocalFileListEvent to registered listeners.
 * The first parameter is the vector of FileInfo. The second 
 * parameter is the current working directory. 
 * 
 * @param fileList java.util.Vector
 * @param currentDir java.lang.String
 */
void sendLocalList(java.util.Vector<FileInfo> fileList,String currentDir) {
    //Create the file list event from the parameters
    LocalFileListEvent localFileListEvent=new LocalFileListEvent(this,fileList,currentDir);

    //Create the clone of file list listener vector
    Vector<LocalFileListListener> newFileListListeners = new Vector<LocalFileListListener>(localFileListListener);
    
    //loop through the vector and call the method of the listener
    for(LocalFileListListener listener: newFileListListeners){
		listener.localFileListReceived(localFileListEvent);
    }   
    return;
}
/**
 * Sends the StatusEvent to registered StatusEventListeners.
 * The StatusEvent carries the current FTP connection status.
 *
 * @param message java.lang.String
 */
void sendMessage(String message) {
    this.status=message;
    
    //Create the status event from the message
    StatusEvent statusEvent=new StatusEvent(this,message);
    
    
    //Create the clone of status listener vector
    Vector<StatusListener> newStatusListener=new Vector<StatusListener>(statusListener);
    
    //loop through the vector and call the method of the listener
    for (StatusListener listener:newStatusListener){
        listener.statusReceived(statusEvent);
    }   
    return;
}
/**
 * Sends RemoteFileListEvent to registered RemoteFileListListeners
 * The first parameter is the vector of FileInfo which contain
 * information about remote files. The second parameter is the 
 * current working directory on the FTP server.
 * 
 * @param fileInfo java.util.Vector
 * @param currentDir java.lang.String
 */
void sendRemoteList (java.util.Vector<FileInfo> fileInfo,String currentDir ) {
    //Create the file list event from the parameters
    RemoteFileListEvent remoteFileListEvent=new RemoteFileListEvent(this,fileInfo,currentDir);
    
    //Create the clone of file list listener vector
    Vector<RemoteFileListListener> newFileListListener = new Vector<RemoteFileListListener>(remoteFileListListener);
    
    //loop through the vector and call the method of the listener
    for(int i=0;i<newFileListListener.size();i++){
        RemoteFileListListener listener=(RemoteFileListListener)newFileListListener.elementAt(i);
        listener.remoteFileListReceived(remoteFileListEvent);
    }   
    return;
}
/**
 * Sets the SocksProxyHost.
 * This method takes a String parameter and sets the SocksProxyHost
 * to be used for connecting to Servers outside firewall.
 *
 * @param   socksProxyHost domain name or the IP address of socksProxyHost.  
 * @exception IllegalArgumentException  If the socksProxyHost argument is null.
 */
public void setSocksProxyHost(String socksProxyHost ) throws IllegalArgumentException{
    if(socksProxyHost!=null){
        this.socksProxyHost=socksProxyHost.trim();
        remoteProtocol.configureSocks(this.socksProxyHost,this.socksProxyPort);
    }
    else{
        throw new IllegalArgumentException("SocksProxyhost is null in set socks proxy host"); 
    }   
    return;
}
/**
 * Sets the SocksProxyPort.
 * This method takes a String parameter and sets the SocksProxyPort
 * to be used for connecting to Servers outside firewall.
 *
 * @param   socksProxyPort  socksProxyPort as a String.
 * @exception IllegalArgumentException If the socksProxyPort argument is null.
 */
public void setSocksProxyPort (String socksProxyPort ) throws IllegalArgumentException {
    if(socksProxyPort!=null){
        this.socksProxyPort=socksProxyPort;
        remoteProtocol.configureSocks(this.socksProxyHost,this.socksProxyPort);
    }
    else{
        throw new   IllegalArgumentException("SocksProxyPort is null in setSocksProxyPort") ;
    }   
    return;
}
/**
 * This method sets the "TYPE"  to be used for data transfer on the
 * data connection. The String parameter can specify "ASCII" for
 * ascii data transfer and "BINARY" for binary data transfer.
 *
 * @param type data transfer type for the FTP connection.
 */
public void setType (String newType ) throws IllegalArgumentException {
    //check that the type parameter is not null
    if(newType!=null){
        if((newType.equalsIgnoreCase("ascii"))||(newType.equalsIgnoreCase("binary"))){
            //Check return 2,for checking command success.
            if (remoteProtocol.setType(newType)==2)
            {
                this.type=newType;
            }   //end of inner if
        }//end of middle if 
    }//end of outer if  
    else {
        throw new IllegalArgumentException("Type is null in set type") ;
    }   
    return;
}
/**
 * Execute the status command on FTP server.
 * This method will execute the "STAT" command on the remote
 * FTP server.
 */
public void status() {
    //just pass this method to the remote protocol
    remoteProtocol.getStatus();
    return;
}
public void noop() {
	//just pass this method to the remote protocol
	remoteProtocol.noop();
	return;
}
}
