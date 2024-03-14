import 'package:doublylist/structures/node.dart';

class MyList<T> {
  Node<T>? _head;

  MyList() {
    _head = null;
  }

  void insert(T t) {
    var newNode = Node(t);

    if (isEmpty) {
      _head = newNode;
    } else {
      var aux = _head;
      while (aux!.next != null) {
        aux = aux.next;
      }
      aux.next = newNode;
      newNode.previous = aux;
    }
  }

  bool exist(T data) {
    var aux = _head;
    while (aux != null && aux.data != data) {
      aux = aux.next;
    }
    return aux != null;
  }

  void remove(T data) {
    var aux = _head;
    Node<T>? previous;
    while (aux != null && aux.data != data) {
      previous = aux;
      aux = aux.next;
    }
    if (aux == null) return;
    if (aux == _head) {
      _head = aux.next;
    } else {
      previous!.next = aux.next;
      aux.next?.previous = previous;
    }
  }

  String showInverted() {
    if (isEmpty) throw  Exception("Lista vacía");
    var buffer = StringBuffer();
    var aux = _head;
    while (aux!.next != null) {
      aux = aux.next;
    }
    while (aux != null) {
      buffer.write('${aux.data} ');
      aux = aux.previous;
    }
    return buffer.toString().trim();
  }

  @override
  bool get isEmpty => _head == null;

  @override
  String toString() {
    if (isEmpty) throw  Exception("Lista vacía");
    var aux = _head;
    var buffer = StringBuffer();
    while (aux != null) {
      buffer.write('${aux.data} ');
      aux = aux.next;
    }
    return buffer.toString().trim();
  }

  void insertFirst(T t) {
    var newNode = Node(t);
    if (isEmpty) {
      _head = newNode;
    } else {
      newNode.next = _head;
      _head!.previous = newNode;
      _head = newNode;
    }
  }

  void insertLast(T t) {
    var newNode = Node(t);

    if (isEmpty) {
      _head = newNode;
    } else {
      var aux = _head;
      while (aux!.next != null) {
        aux = aux.next;
      }
      aux.next = newNode;
      newNode.previous = aux;
    }
  }

  T removeFirst() {
    if (isEmpty) throw  Exception("Lista vacía");
    var data = _head!.data;
    _head = _head!.next;
    if (_head != null) {
      _head!.previous = null;
    }
    return data;
  }

  T removeLast() {
    if (isEmpty) {
      throw Exception("Lista vacía");
    }
    var aux = _head;
    while (aux!.next != null) {
      aux = aux.next;
    }
    var data = aux.data;
    if (aux.previous != null) {
      aux.previous!.next = null;
    } else {
      _head = null;
    }
    return data;
  }
}
