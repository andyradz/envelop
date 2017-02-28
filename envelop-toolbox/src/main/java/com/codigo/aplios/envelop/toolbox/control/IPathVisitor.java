/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.envelop.toolbox.control;

import com.codigo.aplios.envelop.toolbox.domain.IDirectoryInfo;
import com.codigo.aplios.envelop.toolbox.domain.IDriveInfo;
import com.codigo.aplios.envelop.toolbox.domain.IFileInfo;

/**
 *
 * @author andrzej.radziszewski
 */
public interface IPathVisitor {

    void visit(IDriveInfo driveInfo);

    void visit(IDirectoryInfo directoryInfo);

    void visit(IFileInfo fileInfo);

}
