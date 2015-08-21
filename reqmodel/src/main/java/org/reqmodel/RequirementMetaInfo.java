package org.reqmodel;

import org.docmodel.DocModelMetaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequirementMetaInfo {

    @Autowired
    private DocModelMetaInfo docModelMetaInfo;

    public String meinSenf() {
        String docModelSenf = docModelMetaInfo.sayHello();
        return docModelSenf + "\nThe Req Model is also there!";
    }
}
