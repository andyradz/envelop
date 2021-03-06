/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.envelop.explorer.control;

import com.codigo.aplios.envelop.explorer.domain.IDirectoryInfo;
import com.codigo.aplios.envelop.explorer.domain.IDriveInfo;
import com.codigo.aplios.envelop.explorer.domain.IFileInfo;

/**
 *
 * @author andrzej.radziszewski
 */
public interface IPathVisitor {

    void visit(IDriveInfo driveInfo);

    void visit(IDirectoryInfo directoryInfo);

    void visit(IFileInfo fileInfo);

}
