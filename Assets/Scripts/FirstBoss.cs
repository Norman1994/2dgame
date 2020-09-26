using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FirstBoss : Monster {

    private float speed = 1.5f;

    private Vector3 direction;
    public static bool move;
    private float rate = 5.0f;
    private Bullet bullet;
    public static int liveCount;
    private SpriteRenderer sprite;
    private bool shooting;
    public AudioClip shootSound;
    AudioSource audioSource;

    protected override void Awake()
    {
        sprite = GetComponentInChildren<SpriteRenderer>();
        bullet = Resources.Load<Bullet>("Bullet");
        //ch = new Character(); 
    }

    protected override void Start ()
    {
        audioSource = GetComponent<AudioSource>();
        direction = transform.up;
        move = false;
        shooting = false;
        liveCount = 20;
        InvokeRepeating("Attack", 5.0f, 2.0f);
    }

    // Update is called once per frame
    protected override void Update ()
    {
        if (move == true)
        {
            Move();
            shooting = true;
        }
    }

    public void Attack()
    {
        if (shooting == true)
        {
            Shoot();
            audioSource.PlayOneShot(shootSound);
        }
    }

    private void Move()
    {
        transform.position = Vector3.MoveTowards(transform.position, transform.position + direction, speed * Time.deltaTime);
        if (transform.position.y > 8.5f)
        {
            move = false;
        }
    }

    private void Shoot()
    {
        Vector3 position = transform.position;
        position.y += 1.0f;
        position.x += 4.0f;
        Bullet newBullet = Instantiate(bullet, position, bullet.transform.rotation) as Bullet;

        newBullet.Parent = gameObject;
        newBullet.Direction = -newBullet.transform.right;
        
    }

    protected override void OnTriggerEnter2D(Collider2D collider)
    {
        Unit unit = collider.GetComponent<Unit>();

        if (unit && unit is Character)
        {
            unit.ReceiveDamage();
        }
        Bullet bullet = collider.gameObject.GetComponent<Bullet>();

        if (bullet && bullet.Parent != gameObject)
        {
            liveCount--;
            if (liveCount == 0)
                ReceiveDamage();
        }
    }
}
