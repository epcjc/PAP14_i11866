Imports MySql.Data.MySqlClient
Imports System.Data
Public Class responder

    Private Sub UserControl_Loaded(sender As Object, e As RoutedEventArgs) Handles MyBase.Loaded
        IdTextBox.Visibility = System.Windows.Visibility.Hidden
        Dim I11866DataSet As WpfApplication1.i11866DataSet = CType(Me.FindResource("I11866DataSet"), WpfApplication1.i11866DataSet)
        'Load data into the table questoes. You can modify this code as needed.
        Dim I11866DataSetquestoesTableAdapter As WpfApplication1.i11866DataSetTableAdapters.questoesTableAdapter = New WpfApplication1.i11866DataSetTableAdapters.questoesTableAdapter()
        I11866DataSetquestoesTableAdapter.Fill(I11866DataSet.questoes)
        Dim QuestoesViewSource As System.Windows.Data.CollectionViewSource = CType(Me.FindResource("QuestoesViewSource"), System.Windows.Data.CollectionViewSource)
        QuestoesViewSource.View.MoveCurrentToFirst()
    End Sub

    Private Sub Button_Click(sender As Object, e As RoutedEventArgs)
        Dim Query As String

        Dim con As MySqlConnection = New MySqlConnection("Data Source=projetos.epcjc.net;Database=i11866;User ID=i11866;Password=DretNed7;")
        con.Open()
        Query = "Delete FROM questoes WHERE id  =" + IdTextBox.Text

        Dim cmd As MySqlCommand = New MySqlCommand(Query, con)

        Dim i As Integer = cmd.ExecuteNonQuery()
        If (i > 0) Then
            MessageBox.Show("Registo eliminado!")
        Else
            MessageBox.Show("Ocorreu um erro ao eliminar o registo. Verifique a ligação á internet.")
        End If
        con.Close()

    End Sub

    Private Sub Button_Click_1(sender As Object, e As RoutedEventArgs)
        Dim Query As String

        Dim con As MySqlConnection = New MySqlConnection("Data Source=projetos.epcjc.net;Database=i11866;User ID=i11866;Password=DretNed7;")
        con.Open()
        Query = "UPDATE questoes SET resposta ='" + RespostaTextBox.Text + "'"
        Query = Query + " WHERE id = " + IdTextBox.Text

        Dim cmd As MySqlCommand = New MySqlCommand(Query, con)

        Dim i As Integer = cmd.ExecuteNonQuery()
        If (i > 0) Then
            MessageBox.Show("Resposta enviada!")
        Else
            MessageBox.Show("Ocorreu um erro ao enviar a sua resposta. Verifique a ligação á internet.")
        End If
        con.Close()
    End Sub
End Class
