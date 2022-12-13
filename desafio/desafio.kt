enum class Nivel { INICIANTE, INTERMEDIARIO, AVANCADO }
enum class LinguagemProgramacao { JAVA, KOTLIN }

data class Usuario(val nome: String, var idade : Int) {
    fun inscrever(formacao: Formacao) {
        formacao.matricular(this)
        println("${nome} se inscreveu na formação ${formacao.nome}")
    }
}

data class ConteudoEducacional(var nome: String, var duracao: Int, val linguagem : LinguagemProgramacao)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>, val nivel: Nivel) {
    
    val inscritos = mutableListOf<Usuario>()
    
    fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
    }
    
    fun calcularDuracaoTotalHoras(): Int {
        var duracaoTotal = 0
        for(conteudo in conteudos) {
            duracaoTotal += conteudo.duracao
        }
        return duracaoTotal/60
    }
}

fun main() {
    
    // criação dos conteudos
    val cursoKotlin1 = ConteudoEducacional("Conhecendo o Kotlin e Sua Documentação Oficial", 60, LinguagemProgramacao.KOTLIN)
    val cursoKotlin2 = ConteudoEducacional("Introdução Prática à Linguagem de Programação Kotlin", 60, LinguagemProgramacao.KOTLIN)
    val conteudoKotlin = mutableListOf<ConteudoEducacional>(cursoKotlin1, cursoKotlin2)
    
    val cursoJava1 = ConteudoEducacional("Trabalhando com Collections Java", 240, LinguagemProgramacao.JAVA)
    val cursoJava2 = ConteudoEducacional("Tratamento de Exceções em Java", 120, LinguagemProgramacao.JAVA)
    val conteudoJava = mutableListOf<ConteudoEducacional>(cursoJava1, cursoJava2)
    
    // criação das formaçôes
    val formacaoKotlin = Formacao("Kotlin Developer", conteudoKotlin, Nivel.INICIANTE)
    println("A Formação Kotlin Developer tem duração de ${formacaoKotlin.calcularDuracaoTotalHoras()} hora(s), " + 
            "e aborda conteúdos de nível ${formacaoKotlin.nivel.toString().lowercase()}")
    val formacaoJava = Formacao("Java Developer", conteudoJava, Nivel.INTERMEDIARIO)
    println("A Formação Java Developer tem duração de ${formacaoJava.calcularDuracaoTotalHoras()} hora(s), " + 
            "e aborda conteúdos de nível ${formacaoJava.nivel.toString().lowercase()}")
    
    // criação dos usuários
    val ana = Usuario("Ana", 27)
    ana.inscrever(formacaoKotlin)
    val bia = Usuario("Bia", 24)
    bia.inscrever(formacaoKotlin)
    bia.inscrever(formacaoJava)
    
    println("Há ${formacaoKotlin.inscritos.size} usuário(s) inscrito(s) na formação Kotlin Developer")
}