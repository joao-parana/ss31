# my-calendar

## Build & Run

```
./start-app
```

## Config

```
cat WEB-INF/spring/security-ldap-explicitly.xml
```

## Dependências

LDAP Server rodando no **host** corp.jbcpcalendar.com na **porta** 389

```
more /etc/hosts

# Host Database
#
# localhost is used to configure the loopback interface
# when the system is booting.  Do not change this entry.
# Add others entries.
##
127.0.0.1       localhost
127.0.0.1       corp.jbcpcalendar.com
. . . 
```

A classe **org.springframework.security.ldap.authentication.LdapAuthenticationProvider** será invocada e delega a pesquisa no diretório a classe **org.springframework.security.ldap.search.FilterBasedLdapUserSearch** que usa **searchFilter** '(sAMAccountName={0})' e **searchBase** 'CN=Users'

No caso de ocorrer um erro, como por exemplo: java.net.ConnectException: **Connection refused**, a aplicação é desviada para **/my-calendar/login/form?error** para exibir a mensagem.

O AD LDS (**Active Directory Lightweight Directory Services**) pode ser obtido no site da Microsoft em [https://www.microsoft.com/pt-br/download/details.aspx?id=14683](https://www.microsoft.com/pt-br/download/details.aspx?id=14683) e funciona no **Windows 7**

![Active Directory Lightweight Directory Services no Windows 7 64 bits](https://bytebucket.org/parana/spring-security-3-1/raw/c012e24d0bc6dbc11bd35ec284f11e60cf8a1f2a/chapter05.10-calendar/Win7-64bits-Active_Directory_Lightweight_Directory_Services.png?token=36ba8d86fd06a7671f138d0cdf05ed25cececef3)
