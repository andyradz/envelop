/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.envelop.toolbox.domain;

import static com.codigo.aplios.envelop.toolbox.domain.IFileInfo.FileAttributes;
import java.time.LocalDateTime;

/**
 *
 * @author andrzej.radziszewski
 */
public interface IDirectoryInfo {

    FileAttributes attributes();

    LocalDateTime creationTime();

    LocalDateTime creationTimeUtc();

    boolean exists();

    String extension();

    String name();

    String fullName();

    String localName();

    LocalDateTime lastAccessTime();

    LocalDateTime lastAccessTimeUtc();

    LocalDateTime lastWriteTime();

    LocalDateTime lastWriteTimeUtc();

    IDirectoryInfo parent();

    IDirectoryInfo root();

}
