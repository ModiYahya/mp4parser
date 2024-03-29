/*  
 * Copyright 2008 CoreMedia AG, Hamburg
 *
 * Licensed under the Apache License, Version 2.0 (the License); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an AS IS BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */

package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * The Item Protection Box provides an array of item protection information, for use by the Item Information Box.
 *
 * @see com.coremedia.iso.boxes.ItemProtectionBox
 */
public class ItemProtectionBox extends FullContainerBox {

    public static final String TYPE = "ipro";

    public ItemProtectionBox() {
        super(TYPE);
    }

    public SchemeInformationBox getItemProtectionScheme() {
        if (!getBoxes(SchemeInformationBox.class).isEmpty()) {
            return getBoxes(SchemeInformationBox.class).get(0);
        } else {
            return null;
        }
    }
    @Override
    public void _parseDetails(ByteBuffer content) {
        parseVersionAndFlags(content);
        IsoTypeReader.readUInt16(content);
        parseChildBoxes(content);
    }


    @Override
    protected void getContent(ByteBuffer bb) throws IOException {
        writeVersionAndFlags(bb);
        IsoTypeWriter.writeUInt16(bb, getBoxes().size());
        writeChildBoxes(bb);
    }

}
