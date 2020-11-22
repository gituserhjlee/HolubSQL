Holub EmbeddedSQL 기능 확장 및 설계 개선 (design patterns term project)

(1) HTML Exporter 개발: 빌더 패턴이 적용된 기존 exporter 클래스를 참고하여 HTML
exporter를 개발
(2) XML Importer 와 XML Exporter. XML: 위와 유사하게 XML 태그를 이용한 importer와
exporter를 개발
(3) Console (com.holub.database.jdbc 패키지 밑의 Console.java)에서 다음의 쿼리를 수
행하면 에러가 나는데, 소스코드를 수정하여 해당 쿼리가 잘 수행되도록 기능 확장.
기존 구현의 문제점을 지적하고 고친 코드를 설명할 것.
select * from address, name where address.addrId = name.addrId
 참고로, 프로그램에서 조인 기능 자체는 잘 동작함. 다음 쿼리는 에러를 발생하지 않음
select street from address, name where address.addrId = name.addrId
(4) 기존 설계에 Visitor pattern을 통해 구현할 수 있는 기능을 제안하고 그것을 구현


HolubSQL.jar
Version 1.0
Released 27 Sept., 2004

/*  (c) 2004 Allen I. Holub. All rights reserved.
 *
 *  This code may be used freely by yourself with the following
 *  restrictions:
 *
 *  o Your splash screen, about box, or equivalent, must include
 *    Allen Holub's name, copyright, and URL. For example:
 *
 *      This program contains Allen Holub's SQL package.<br>
 *      (c) 2005 Allen I. Holub. All Rights Reserved.<br>
 *              http://www.holub.com<br>
 *
 *    If your program does not run interactively, then the foregoing
 *    notice must appear in your documentation.
 *
 *  o You may not redistribute (or mirror) the source code.
 *
 *  o You must report any bugs that you find to me. Use the form at
 *    http://www.holub.com/company/contact.html or send email to
 *    allen@Holub.com.
 *
 *  o The software is supplied <em>as is</em>. Neither Allen Holub nor
 *    Holub Associates are responsible for any bugs (or any problems
 *    caused by bugs, including lost productivity or data)
 *    in any of this code.
 */
