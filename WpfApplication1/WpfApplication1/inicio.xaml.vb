Public Class inicio
    Private Sub connfigura_Click(sender As Object, e As RoutedEventArgs) Handles connfigura.Click
        Dim webAddress As String = "http://projetos.epcjc.net/~i11866/backoffice/login.php"
        Process.Start(webAddress)
    End Sub

    Private Sub site_Click(sender As Object, e As RoutedEventArgs) Handles site.Click
        Dim webAddress As String = "http://projetos.epcjc.net/~i11866/"
        Process.Start(webAddress)
    End Sub

    Private Sub basedados_Click(sender As Object, e As RoutedEventArgs) Handles basedados.Click
        Dim webAddress As String = "http://projetos.epcjc.net/phpmyadmin/"
        Process.Start(webAddress)

    End Sub

    Private Sub sair1_Click(sender As Object, e As RoutedEventArgs) Handles sair1.Click
        Dim janela As Window
        Dim frm As login = New login
        janela = Window.GetWindow(Me)
        frm.Show()

        janela.Close()
    End Sub
End Class
