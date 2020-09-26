using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraScript : MonoBehaviour 
{
	public Transform target;
	public float relativeHeigth = 15.0f;
	public float zDistance = 10.0f;
	public float dampSpeed = 2;
	public float xDistance = 10.0f;

	public float xRotate = 45.0f;
	public float yRotate = -45.0f;
	public float zRotate = 0;



	void Update () {
		Vector3 newPos = target.position + new Vector3(xDistance, relativeHeigth, -zDistance);
		transform.position = Vector3.Lerp(transform.position, newPos, Time.smoothDeltaTime*dampSpeed);

		Quaternion rotateThis = Quaternion.Euler(xRotate, yRotate, zRotate);
		transform.rotation = Quaternion.Slerp (transform.rotation, rotateThis, Time.smoothDeltaTime * 2);       
	}
}
