Imports MySql.Data.MySqlClient
Imports System.Data
Public Class destaques

    Private Sub UserControl_Loaded(sender As Object, e As RoutedEventArgs) Handles MyBase.Loaded
        IdTextBox.Visibility = System.Windows.Visibility.Hidden
        Dim I11866DataSet As WpfApplication1.i11866DataSet = CType(Me.FindResource("I11866DataSet"), WpfApplication1.i11866DataSet)
        'Load data into the table destaques. You can modify this code as needed.
        Dim I11866DataSetdestaquesTableAdapter As WpfApplication1.i11866DataSetTableAdapters.destaquesTableAdapter = New WpfApplication1.i11866DataSetTableAdapters.destaquesTableAdapter()
        I11866DataSetdestaquesTableAdapter.Fill(I11866DataSet.destaques)
        Dim DestaquesViewSource As System.Windows.Data.CollectionViewSource = CType(Me.FindResource("DestaquesViewSource"), System.Windows.Data.CollectionViewSource)
        DestaquesViewSource.View.MoveCurrentToFirst()
    End Sub


    Private Sub Button_Click(sender As Object, e As RoutedEventArgs)
        Dim Query As String

        Dim con As MySqlConnection = New MySqlConnection("Data Source=projetos.epcjc.net;Database=i11866;User ID=i11866;Password=DretNed7;")
        con.Open()
        Query = "Delete FROM destaques WHERE id  =" + IdTextBox.Text

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
        'Query = "INSERT INTO  userreg"
        Dim con As MySqlConnection = New MySqlConnection("Data Source=projetos.epcjc.net;Database=i11866;User ID=i11866;Password=DretNed7;")
        'Dim sql As MySqlCommand = New MySqlCommand(Query, con)

        Query = "INSERT INTO destaques(titulo,corpo)VALUES('" + addtitulo.Text + "','" + addcorpo.Text + "'" + ")"



        con.Open()

        Dim cmd As MySqlCommand = New MySqlCommand(Query, con)

        Dim i As Integer = cmd.ExecuteNonQuery()
        If (i > 0) Then
            MessageBox.Show("Nova Informação foi inserida")
        Else
            MessageBox.Show("Ocorreu um erro. Verifique a ligação á internet")
        End If

        con.Close()
    End Sub

    Private Sub Button_Click_2(sender As Object, e As RoutedEventArgs)
        Dim Query As String

        Dim con As MySqlConnection = New MySqlConnection("Data Source=projetos.epcjc.net;Database=i11866;User ID=i11866;Password=DretNed7;")
        con.Open()
        Query = "UPDATE destaques SET titulo ='" + TituloTextBox.Text + "',"
        Query = Query + "corpo = '" + CorpoTextBox.Text + "'"
        Query = Query + " WHERE id = " + IdTextBox.Text

        Dim cmd As MySqlCommand = New MySqlCommand(Query, con)

        Dim i As Integer = cmd.ExecuteNonQuery()
        If (i > 0) Then
            MessageBox.Show("Dados atualizados!")
        Else
            MessageBox.Show("Ocorreu um erro. Verifique a ligação á internet.")
        End If
        con.Close()
    End Sub
End Class
