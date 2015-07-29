/**
 * SimpleServerInfo.java
 * Title: Oscar Wei Web Project
 * Copyright: Copyright(c)2015, oscarwei168
 *
 * @author Oscar Wei
 * @since 2015/7/2
 * <p>
 * H i s t o r y
 * 2015/7/2 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.mail;

import com.google.common.base.MoreObjects;
import tw.com.oscar.spring.util.mail.exception.OscarMailException;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 * Title: SimpleServerInfo.java
 * </p>
 * <strong>Description:</strong> A SMTP server information implementation <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/7/2
 * @since 2015/7/2
 */
public class SimpleServerInfo implements ServerInfo, Serializable {
    
    private Optional<Boolean> ssl = Optional.of(false);
    private String host;
    private Optional<Integer> port = Optional.of(25);
    private String username;
    private Optional<String> password = Optional.of("");
    
    /**
     * A setter for 'ssl' property
     *
     * @param ssl a value for 'ssl' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Override
    public void setSsl(Optional<Boolean> ssl) throws OscarMailException {
        this.ssl = ssl;
    }
    
    /**
     * A getter method for property 'ssl' value
     *
     * @return the property 'ssl' value
     */
    public Optional<Boolean> getSsl() {
        return ssl;
    }
    
    /**
     * A setter for 'host' property
     *
     * @param host a value for 'host' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Override
    public void setHost(String host) throws OscarMailException {
        this.host = host;
    }
    
    /**
     * A getter method for property 'host' value
     *
     * @return the property 'host' value
     */
    public String getHost() {
        return host;
    }
    
    /**
     * A setter for 'port' property
     *
     * @param port a value for 'port' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Override
    public void setPort(Optional<Integer> port) throws OscarMailException {
        this.port = port;
    }
    
    /**
     * A getter method for property 'port' value
     *
     * @return the property 'port' value
     */
    public Optional<Integer> getPort() {
        return port;
    }
    
    /**
     * A setter for 'username' property
     *
     * @param username a value for 'username' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Override
    public void setUsername(String username) throws OscarMailException {
        this.username = username;
    }
    
    /**
     * A getter method for property 'username' value
     *
     * @return the property 'username' value
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * A setter for 'password' property
     *
     * @param password a value for 'password' property
     * @throws OscarMailException Throw exception when:<br>If any exception occurred
     */
    @Override
    public void setPassword(Optional<String> password) throws OscarMailException {
        this.password = password;
    }
    
    /**
     * A getter method for property 'password' value
     *
     * @return the property 'password' value
     */
    protected Optional<String> getPassword() {
        return password;
    }
    
    /**
     * A equals() override implementation
     *
     * @param o a object
     * @return true if equals, or false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        final SimpleServerInfo that = (SimpleServerInfo) o;
        
        return Objects.equals(this.getHost(), that.getHost())
                && Objects.equals(this.getPort(), that.getPort())
                && Objects.equals(this.getUsername(), that.getUsername())
                && Objects.equals(this.getPassword(), that.getPassword());
        
    }
    
    /**
     * A hashCode() override implementation
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(this.getHost(),
                this.getPort(), this.getUsername(), this.getPassword());
    }
    
    /**
     * A toString() override implementation
     *
     * @return a pre-formatter string
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("host", this.getHost()).
                add("port", this.getPort()).add("username", this.getUsername()).toString();
    }
}
