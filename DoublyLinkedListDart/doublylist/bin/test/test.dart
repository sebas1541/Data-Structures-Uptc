import 'package:doublylist/structures/mylist.dart';

void main() {
  var doublylist = MyList<int>();

  doublylist.insert(8);
  doublylist.insert(16);
  doublylist.insert(46);

  print('Lista: ${doublylist.toString()}');

  doublylist.remove(16);

  print('Removiendo 16: ${doublylist.toString()}');

  print('Lista invertida: ${doublylist.showInverted()}');

  print('La lista contiene el elemento 8? ${doublylist.exist(8)}');
  print('La lista contiene el elemento 16? ${doublylist.exist(16)}');

  doublylist.insertFirst(2);
  print('Después de insertar 2 al inicio: ${doublylist.toString()}');

  doublylist.insertLast(64);
  print('Después de insertar 64 al final: ${doublylist.toString()}');

  var removedFirst = doublylist.removeFirst();
  print(
      'Después de remover el primer nodo ($removedFirst): ${doublylist.toString()}');

  var removedLast = doublylist.removeLast();
  print(
      'Después de remover el último nodo ($removedLast): ${doublylist.toString()}');
}
