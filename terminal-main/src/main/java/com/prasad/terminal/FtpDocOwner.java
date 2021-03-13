

package com.prasad.terminal;

import mpTOOLS.mpEDIT.DocInterface;
import mpTOOLS.mpEDIT.ViewInterface;
import mpTOOLS.mpEDIT.DocOwnerInterface;

public class FtpDocOwner implements DocOwnerInterface {
    PanelFtpRemote panel;
    boolean upload;
    boolean isTemp;
    String localName;
    String remotePath;

    public FtpDocOwner(PanelFtpRemote panelFtpRemote, boolean updateAfterEditing, boolean isTmpFile, String localFileName, String remoteFilePath) {
        panel = panelFtpRemote;
        upload = updateAfterEditing;
        isTemp = isTmpFile;
        localName = localFileName;
        remotePath = remoteFilePath;
    }

    public void close() {
        if (isTemp)    // this is a temporary file, remove it
        {
            FileTemp f = new FileTemp(localName, ((PalTerm) panel.getApplet()).isNAV, ((PalTerm) panel.getApplet()).isIE);
            f.delete();
        }
    }

    @Override
    public void savedDoc(DocInterface doc) {
        //TODO: implement this mpTOOLS.mpEDIT.DocOwnerInterface method;
        if (upload
            && panel != null
            && remotePath != null) {
            panel.ftpcmd_upload(localName, false, remotePath);
        }
    }

    @Override
    public void savedAsDoc(DocInterface doc, String filename) {
        //TODO: implement this mpTOOLS.mpEDIT.DocOwnerInterface method;
    }

    @Override
    public void openedView(ViewInterface view) {
        //TODO: implement this mpTOOLS.mpEDIT.DocOwnerInterface method;
    }

    @Override
    public void closingView(ViewInterface view) {
        //TODO: implement this mpTOOLS.mpEDIT.DocOwnerInterface method;
    }

    @Override
    public void docAction(DocInterface doc, String action) {
        //TODO: implement this mpTOOLS.mpEDIT.DocOwnerInterface method;
    }

    @Override
    public void viewAction(ViewInterface view, String action) {
        //TODO: implement this mpTOOLS.mpEDIT.DocOwnerInterface method;
    }
}