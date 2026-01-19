* cyber security: hardening systems against unauthed access
* **information risks** and **vulnerabilities**

what is information risk? it includes unauthed access, interception (mitm, monitored), impersonation, disclosure, destruction

cyber attack purposes:
    * extortion ()
    * disruption

importance of cybersecurity
* valuable data includes: 
    * PII, PHI (identifiable information, health information)
    * trade secrets
    * government confidential information

Regulatory compliance: HIPAA, SOC, PCI DSS, GDPR, PDPO

Pillars of security
CIA:
    * confidentiality: permitting authed access to specific groups, but protect against improper disclosure
        * stealing data
    * integrity: data is complete, accurate, consistent and useful (can't be modified by others)
        * modifying data
    * availability: system & services are available/good uptime
        * destroying data
        * common SLAs are >= 99.9999% (and they usually say how many 9s instead of an actual number)

    # non core stuff, but builds upon the three
    * Non-repudiation
    * Privacy
        * right to control how PI is stored

AAA:
    * AuthN: user identification
    * AuthZ: policy enforcement (roles)
    * accounting: the item being accessed (what have x done?/auditing)

    AuthN:
        * knowledge based: password (something you know)
        * token based: tokens, passkeys (something you have)
        * characteristic based: biometrics (something you are)

    Protocols:
        * radius:L you know what it is
        * tacacs+: cisco stuff
            * tcp/49
            * cisco proprietary
        * diameter:
            * new generation radius
        * kerberos: FUCK YOU !!!!!
        * LDAP
