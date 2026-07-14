package com.graht.aichat.ai.credential;

import com.graht.aichat.ai.model.ModelType;

/**
 * @author GRAHT
 */

public interface CredentialProvider {

    Credential getCredential(CredentialContext  context);
}
