/*
 * Copyright (c) 2005, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
/*
 * $Id: KeyValue.java,v 1.4 2005/05/10 16:35:35 mullan Exp $
 */
package javax.xml.crypto.dsig.keyinfo;

import java.security.KeyException;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.RSAPublicKey;
import javax.xml.crypto.XMLStructure;

/**
 * A representation of the XML <code>KeyValue</code> element as defined
 * in the <a href="http://www.w3.org/TR/xmldsig-core/">
 * W3C Recommendation for XML-Signature Syntax and Processing</a>. A
 * <code>KeyValue</code> object contains a single public key that may be
 * useful in validating the signature. The XML schema definition is defined as:
 *
 * <pre>
 *    <element name="KeyValue" type="ds:KeyValueType"/>
 *    <complexType name="KeyValueType" mixed="true">
 *      <choice>
 *        <element ref="ds:DSAKeyValue"/>
 *        <element ref="ds:RSAKeyValue"/>
 *        <any namespace="##other" processContents="lax"/>
 *      </choice>
 *    </complexType>
 *
 *    <element name="DSAKeyValue" type="ds:DSAKeyValueType"/>
 *    <complexType name="DSAKeyValueType">
 *      <sequence>
 *        <sequence minOccurs="0">
 *          <element name="P" type="ds:CryptoBinary"/>
 *          <element name="Q" type="ds:CryptoBinary"/>
 *        </sequence>
 *        <element name="G" type="ds:CryptoBinary" minOccurs="0"/>
 *        <element name="Y" type="ds:CryptoBinary"/>
 *        <element name="J" type="ds:CryptoBinary" minOccurs="0"/>
 *        <sequence minOccurs="0">
 *          <element name="Seed" type="ds:CryptoBinary"/>
 *          <element name="PgenCounter" type="ds:CryptoBinary"/>
 *        </sequence>
 *      </sequence>
 *    </complexType>
 *
 *    <element name="RSAKeyValue" type="ds:RSAKeyValueType"/>
 *    <complexType name="RSAKeyValueType">
 *      <sequence>
 *        <element name="Modulus" type="ds:CryptoBinary"/>
 *        <element name="Exponent" type="ds:CryptoBinary"/>
 *      </sequence>
 *    </complexType>
 * </pre>
 * A <code>KeyValue</code> instance may be created by invoking the
 * {@link KeyInfoFactory#newKeyValue newKeyValue} method of the
 * {@link KeyInfoFactory} class, and passing it a {@link
 * java.security.PublicKey} representing the value of the public key. Here is
 * an example of creating a <code>KeyValue</code> from a {@link DSAPublicKey}
 * of a {@link java.security.cert.Certificate} stored in a
 * {@link java.security.KeyStore}:
 * <pre>
 * KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
 * PublicKey dsaPublicKey = keyStore.getCertificate("myDSASigningCert").getPublicKey();
 * KeyInfoFactory factory = KeyInfoFactory.getInstance("DOM");
 * KeyValue keyValue = factory.newKeyValue(dsaPublicKey);
 * </pre>
 *
 * This class returns the <code>DSAKeyValue</code> and
 * <code>RSAKeyValue</code> elements as objects of type
 * {@link DSAPublicKey} and {@link RSAPublicKey}, respectively. Note that not
 * all of the fields in the schema are accessible as parameters of these
 * types.
 *
 * @author Sean Mullan
 * @author JSR 105 Expert Group
 * @since 1.6
 * @see KeyInfoFactory#newKeyValue(PublicKey)
 */
public interface KeyValue extends XMLStructure {

    /**
     * URI identifying the DSA KeyValue KeyInfo type:
     * http://www.w3.org/2000/09/xmldsig#DSAKeyValue. This can be specified as
     * the value of the <code>type</code> parameter of the
     * {@link RetrievalMethod} class to describe a remote
     * <code>DSAKeyValue</code> structure.
     */
    final static String DSA_TYPE =
        "http://www.w3.org/2000/09/xmldsig#DSAKeyValue";

    /**
     * URI identifying the RSA KeyValue KeyInfo type:
     * http://www.w3.org/2000/09/xmldsig#RSAKeyValue. This can be specified as
     * the value of the <code>type</code> parameter of the
     * {@link RetrievalMethod} class to describe a remote
     * <code>RSAKeyValue</code> structure.
     */
    final static String RSA_TYPE =
        "http://www.w3.org/2000/09/xmldsig#RSAKeyValue";

    /**
     * Returns the public key of this <code>KeyValue</code>.
     *
     * @return the public key of this <code>KeyValue</code>
     * @throws KeyException if this <code>KeyValue</code> cannot be converted
     *    to a <code>PublicKey</code>
     */
    PublicKey getPublicKey() throws KeyException;
}
