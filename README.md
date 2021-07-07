# App de Cartão de Visitas em Kotlin

Aplicativo de lista de cartão de visita onde é possível manter os dados nome, empresa, telefone de contato, email e cor de fundo do cartão utilizando banco de dados Room.

Este Projeto foi apresentado durante o BootCamp [Inter Android Developer](https://web.digitalinnovation.one/track/inter-android-developer) na [Digital Innovation One](https://web.digitalinnovation.one/).

O Professor e **Igor Baglliotti** nos passou este projeto e nos incubiu de melhorá-lo!




## Alterações Feitas
### Activity de Inclusão de Cards:

- Implementado um botão para a escolha de cores, utilizando a ferramenta: Material ColorPicker ( https://github.com/Dhaval2404/ColorPicker ).
- Implementado o plugin  PhoneNumberKit (https://reposhub.com/android/utils/ibrahimsn98-PhoneNumberKit.html) para verificar e formatar os números de telefone
- Implementado a função  isValidEmail ( https://stackoverflow.com/questions/1819142/how-should-i-validate-an-e-mail-address ) para a verificação se o email digitado é válido.
- Ao Salvar Cartão, o sistema faz a verificação dos campos e mantem na mesma Activity caso email, telefone forem inválidos ou vazios assim como o nome vazio. 

### Activity Cards:

- foi alterado o formato do card para 21:9 
- Por motivo da dificuldade no contraste das corres com o fundo dos cards, foi implementado uma barra lateral ao invés do fundo colorido em cada Card.

 

## #TODO

Na implementação do **Compartilhamento do Cartão**, está ocorrendo o erro, <u>android.os.FileUriExposedException:</u> que pelo visto está relacionado à permissões de acesso. 



**contatos do autor:**

Igor Baglliotti : [![Linkedin Badge](https://img.shields.io/badge/-Igor_Bagliotti-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://br.linkedin.com/in/igor-rotondo-bagliotti-b1612b69)](https://br.linkedin.com/in/igor-rotondo-bagliotti-b1612b69) ![Gmail Badge](https://img.shields.io/badge/-igor.bagliotti@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:igor.bagliotti@gmail.com)
